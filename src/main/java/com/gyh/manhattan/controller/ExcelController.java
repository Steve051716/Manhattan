package com.gyh.manhattan.controller;

import com.alibaba.fastjson.JSON;
import com.gyh.manhattan.dao.SysLevelDAO;
import com.gyh.manhattan.domain.SysLevel;
import com.gyh.manhattan.domain.UserInfo;
import com.gyh.manhattan.model.UserInfoModel;
import com.gyh.manhattan.service.ExcelService;
import com.gyh.manhattan.service.SysLevelService;
import com.gyh.manhattan.service.UserInfoService;
import com.gyh.manhattan.thread.ExcelParseThread;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.*;

@Controller
@RequestMapping(value = "/excel")
public class ExcelController {

    private static final Logger LOG = LoggerFactory.getLogger(ExcelController.class);
    @Inject
    private ExcelService excelService;

    @RequestMapping(value = "/multi", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String importWithMultiThread(Model model, @RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
        Date startDate = new Date();
        List<UserInfoModel> resultList = this.parseExcelByThread(file);
        // String json = JSON.toJSONString(resultList.size());
        Date endDate = new Date();
        float diff = endDate.getTime() - startDate.getTime();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("size", resultList.size());
        resultMap.put("diff", diff);
        String json = JSON.toJSONString(resultMap);
        return json;
    }

    @RequestMapping(value = "/single", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    @ResponseBody
    public String importWithSingleThread(@RequestParam("file") MultipartFile file) throws Exception {
        Date startDate = new Date();
        List<UserInfoModel> resultList = this.parseExcel(file);
        // String json = JSON.toJSONString(resultList.size());
        Date endDate = new Date();
        float diff = endDate.getTime() - startDate.getTime();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("size", resultList.size());
        resultMap.put("diff", diff);
        String json = JSON.toJSONString(resultMap);
        return json;
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/demo/{id:^\\d+$}")
    public String generateExcel(@PathVariable("id") int range, HttpServletResponse response) throws Exception {
        String fileName = "text.xls";
        List<UserInfoModel> userList = this.generateUserList(range);
        LOG.info(JSON.toJSONString(userList));
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet resultSheet = workbook.createSheet("result");
        createTitle(workbook, resultSheet);
        //新增数据行，并且设置单元格数据
        int rowNum=1;
        for(UserInfoModel user : userList){
            HSSFRow row = resultSheet.createRow(rowNum);
            row.createCell(0).setCellValue(user.getName());
            row.createCell(1).setCellValue(user.getAge());
            row.createCell(2).setCellValue(user.getLinkName());
            row.createCell(3).setCellValue(user.getLevel());
            rowNum++;
        }
        //生成excel文件
        // buildExcelFile(fileName, workbook);
        //浏览器下载excel
        buildExcelDocument(fileName, workbook, response);
        return null;
    }

    private List<UserInfoModel> generateUserList(int range) {
        List<UserInfoModel> userList = new ArrayList<>();
        int[] ageArray = new int[50];
        for (int i=0; i<ageArray.length; i++) {
            ageArray[i] = i+18;
        }
        String[] linkNameArray = {"admin", "gao-yh"};
        String[] levelArray = {"normal", "gold", "platinum", "diamond"};
        for (int i=1; i<=range; i++) {
            double random = Math.random();
            int ageRandom = (int)(random*ageArray.length);
            int linkNameRandom = (int)(random*linkNameArray.length);
            int levelRandom = (int)(random*levelArray.length);
            UserInfoModel userInfoModel = new UserInfoModel();
            userInfoModel.setName("用户" + i);
            userInfoModel.setAge(ageArray[ageRandom]);
            userInfoModel.setLinkName(linkNameArray[linkNameRandom]);
            userInfoModel.setLevel(levelArray[levelRandom]);
            userList.add(userInfoModel);
        }
        return userList;
    }

    /**
     * 创建表头
     * @param workbook
     * @param sheet
     */
    private void createTitle(HSSFWorkbook workbook, HSSFSheet sheet){
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("用户名");
        row.createCell(1).setCellValue("年龄");
        row.createCell(2).setCellValue("对接人");
        row.createCell(3).setCellValue("等级");
    }

    /**
     * 生成excel文件
     * @param filename
     * @param workbook
     * @throws Exception
     */
    protected void buildExcelFile(String filename,HSSFWorkbook workbook) throws Exception{
        FileOutputStream fos = new FileOutputStream(filename);
        workbook.write(fos);
        fos.flush();
        fos.close();
    }

    /**
     * 下载excel
     * @param filename
     * @param workbook
     * @param response
     * @throws Exception
     */
    protected void buildExcelDocument(String filename,HSSFWorkbook workbook,HttpServletResponse response) throws Exception{
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "utf-8"));
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    /**
     *
     * @param file
     * @return
     */
    private List<UserInfoModel> parseExcelByThread(MultipartFile file) throws Exception {
        Sheet sheet = this.getSheet(file);
        List<UserInfoModel> userList = new ArrayList<>();

        Date startDate = new Date();
        // 创建一个线程池
        // ExecutorService pool = Executors.newFixedThreadPool(1);
        // 创建两个有返回值的任务
        // 1-201

        /*int[] startIndexArray = {1, 201, 401, 601, 801};
        int[] endIndexArray = {200, 400, 600, 800, 1000};
        for (int i=0; i<5; i++) {
            Callable callable = new ExcelParseThread(excelService, sheet, startIndexArray[i], endIndexArray[i]);
            FutureTask<List<UserInfoModel>> futureTask = new FutureTask<>(callable);
            Thread thread = new Thread(futureTask);
            thread.start();
            userList.addAll(futureTask.get());
        }*/


        Callable c1 = new ExcelParseThread(excelService, sheet, 1, 200);
        Callable c2 = new ExcelParseThread(excelService, sheet, 201, 400);
        Callable c3 = new ExcelParseThread(excelService, sheet, 401, 600);
        Callable c4 = new ExcelParseThread(excelService, sheet, 601, 800);
        Callable c5 = new ExcelParseThread(excelService, sheet, 801, 1000);


        FutureTask<List<UserInfoModel>> task1 = new FutureTask<>(c1);
        FutureTask<List<UserInfoModel>> task2 = new FutureTask<>(c2);
        FutureTask<List<UserInfoModel>> task3 = new FutureTask<>(c3);
        FutureTask<List<UserInfoModel>> task4 = new FutureTask<>(c4);
        FutureTask<List<UserInfoModel>> task5 = new FutureTask<>(c5);

        Thread thread1 = new Thread(task1);
        thread1.start();
        Thread thread2 = new Thread(task2);
        thread2.start();
        Thread thread3 = new Thread(task3);
        thread3.start();
        Thread thread4 = new Thread(task4);
        thread4.start();
        Thread thread5 = new Thread(task5);
        thread5.start();

        userList.addAll(task1.get());
        userList.addAll(task2.get());
        userList.addAll(task3.get());
        userList.addAll(task4.get());
        userList.addAll(task5.get());

        Date endDate = new Date();
        LOG.info((endDate.getTime() - startDate.getTime()) + "");
        return userList;
    }

    /**
     *
     * @param file
     * @return
     */
    private List<UserInfoModel> parseExcel(MultipartFile file) throws Exception {
        Sheet sheet = this.getSheet(file);
        Date startDate = new Date();
        List<UserInfoModel> userList = excelService.parseExcel(sheet);
        Date endDate = new Date();
        LOG.info((endDate.getTime() - startDate.getTime()) + "");
        return userList;
    }

    /**
     *
     * @return
     */
    private Sheet getSheet(MultipartFile file) {
        Workbook workbook = null;
        InputStream fileStream = null;
        String filePath = file.getOriginalFilename();
        if (StringUtils.isEmpty(filePath)) {
            // return "无文件名！";
        }
        if (!filePath.toLowerCase().endsWith(".xls") && !filePath.toLowerCase().endsWith(".xlsx")) {
            // return "文件不是excel类型";
        }
        try {
            fileStream = (InputStream) file.getInputStream();
            if (filePath.toLowerCase().endsWith(".xls")) {
                // 创建 Excel 2003 工作簿对象
                workbook = new HSSFWorkbook(fileStream);
            } else {
                // 创建 Excel 2007 工作簿对象
                workbook = new XSSFWorkbook(fileStream);
            }
        } catch (Exception e) {
            try {
                fileStream.close();
            } catch (IOException e1) {

            }
        }
        return workbook.getSheetAt(0);
    }
}
