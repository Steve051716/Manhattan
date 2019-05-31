package com.gyh.manhattan.thread;

import com.gyh.manhattan.model.UserInfoModel;
import com.gyh.manhattan.service.ExcelService;
import org.apache.poi.ss.usermodel.Sheet;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author
 */
public class ExcelParseThread implements Callable {

    private ExcelService excelService;
    private Sheet sheet;
    private int startIndex;
    private int endIndex;

    public ExcelParseThread(ExcelService excelService, Sheet sheet, int startIndex, int endIndex) {
        this.excelService = excelService;
        this.sheet = sheet;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }

    public ExcelService getExcelService() {
        return excelService;
    }

    public void setExcelService(ExcelService excelService) {
        this.excelService = excelService;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public List<UserInfoModel> call() throws Exception {
        return excelService.parseExcel(sheet, startIndex, endIndex);
    }
}
