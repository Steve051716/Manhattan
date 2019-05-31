package com.gyh.manhattan.service.impl;

import com.gyh.manhattan.domain.SysLevel;
import com.gyh.manhattan.domain.UserInfo;
import com.gyh.manhattan.model.UserInfoModel;
import com.gyh.manhattan.service.ExcelService;
import com.gyh.manhattan.service.SysLevelService;
import com.gyh.manhattan.service.UserInfoService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Inject
    private UserInfoService userInfoService;
    @Inject
    private SysLevelService sysLevelService;

    @Override
    public List<UserInfoModel> parseExcel(Sheet sheet) {
        return this.parseExcel(sheet, 1, sheet.getLastRowNum());
    }

    /**
     * @param sheet
     * @param startIndex
     * @param endIndex
     * @return
     */
    @Override
    public List<UserInfoModel> parseExcel(Sheet sheet, int startIndex, int endIndex) {
        List<UserInfoModel> userInfoModelList = new ArrayList<>();
        startIndex = startIndex > 1 ? startIndex : 1;
        endIndex = endIndex > sheet.getLastRowNum() + 1 ? sheet.getLastRowNum() + 1 : endIndex;
        for (int i = startIndex; i <=endIndex; i++) {
            Row row = sheet.getRow(i);
            String name = row.getCell(0).getStringCellValue();
            int age = (int)row.getCell(1).getNumericCellValue();
            String linkName = row.getCell(2).getStringCellValue();
            Map<String, Object> linkParams = new HashMap<>(16);
            linkParams.put("name", linkName);
            UserInfo userInfo = userInfoService.findOneRecordByParam(linkParams);
            if (userInfo == null) {
                return null;
            }
            String level = row.getCell(3).getStringCellValue();
            Map<String, Object> levelParams = new HashMap<>(16);
            levelParams.put("name", level);
            SysLevel sysLevel = sysLevelService.findOneRecordByParam(levelParams);
            if (sysLevel == null) {
                return null;
            }
            UserInfoModel userInfoModel = new UserInfoModel();
            userInfoModel.setName(name);
            userInfoModel.setAge(age);
            userInfoModel.setLinkName(linkName);
            userInfoModel.setLevel(level);
            userInfoModelList.add(userInfoModel);
        }
        return userInfoModelList;
    }
}
