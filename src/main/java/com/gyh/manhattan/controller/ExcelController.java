package com.gyh.manhattan.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/excel")
public class ExcelController {

    private static final Logger LOG = LoggerFactory.getLogger(ExcelController.class);

    @RequestMapping(value = "/multi", produces = "application/json; charset=utf-8")
    public String importWithMultiThread(Model model, @RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
        return null;
    }

    @RequestMapping(value = "/single", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String importWithSingleThread(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request) throws Exception {
        List<ExcelData> resultList = this.parseExcel(file);
        String json = JSON.toJSONString(resultList);
        LOG.info(json);
        return json;
    }

    class ExcelData {
        private String value1;
        private String value2;
        private String value3;
        private String value4;
        private String value5;

        public String getValue1() {
            return value1;
        }

        public void setValue1(String value1) {
            this.value1 = value1;
        }

        public String getValue2() {
            return value2;
        }

        public void setValue2(String value2) {
            this.value2 = value2;
        }

        public String getValue3() {
            return value3;
        }

        public void setValue3(String value3) {
            this.value3 = value3;
        }

        public String getValue4() {
            return value4;
        }

        public void setValue4(String value4) {
            this.value4 = value4;
        }

        public String getValue5() {
            return value5;
        }

        public void setValue5(String value5) {
            this.value5 = value5;
        }
    }

    /**
     *
     * @param file
     * @return
     */
    private List<ExcelData> parseExcel(MultipartFile file) {
        List<ExcelData> excelDataList = new ArrayList<>();
        List<String[]> result = new ArrayList<String[]>();
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
        Sheet sheet = workbook.getSheetAt(0);
        for (int i = 1; i <sheet.getLastRowNum()+1; i++) {
            ExcelData excelData = new ExcelData();
            Row row = sheet.getRow(i);
            excelData.setValue1(row.getCell(0).getStringCellValue());
            excelData.setValue2(row.getCell(1).getStringCellValue());
            excelData.setValue3(row.getCell(2).getStringCellValue());
            excelData.setValue4(row.getCell(3).getStringCellValue());
            excelData.setValue5(row.getCell(4).getStringCellValue());
            excelDataList.add(excelData);
        }
        return excelDataList;
    }
}
