package com.gyh.manhattan.service;

import com.gyh.manhattan.model.UserInfoModel;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

/**
 * @author
 */
public interface ExcelService {
    /**
     *
     * @param sheet
     * @return
     */
    public List<UserInfoModel> parseExcel(Sheet sheet);

    /**
     *
     * @param sheet
     * @return
     */
    public List<UserInfoModel> parseExcel(Sheet sheet, int startIndex, int endIndex);
}
