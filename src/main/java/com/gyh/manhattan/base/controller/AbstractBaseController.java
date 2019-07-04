package com.gyh.manhattan.base.controller;

import com.github.pagehelper.PageHelper;
import com.gyh.manhattan.base.service.BaseService;
import com.gyh.manhattan.common.ExecuteResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 抽象controller基类
 * @author gaoyuhang
 */
public abstract class AbstractBaseController<T> {

    @Inject
    protected BaseService<T> service;

    /**
     * 查看多条数据
     * @param request
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    protected ExecuteResult<T> findRecords(HttpServletRequest request) {
        ExecuteResult<T> executeResult = new ExecuteResult<>();
        Map<String, Object> params = new HashMap<>(16);
        params.put("id", request.getParameter("id"));
        // PageHelper.startPage(1, 10);
        List<T> recordList = service.findRecords(params);
        executeResult.setResultList(recordList);
        return executeResult;
    }

    /**
     * 查看单条数据
     * @param id
     * @return
     */
    @RequestMapping(value = "/record/{id:^\\d+$}", method = RequestMethod.GET)
    protected ExecuteResult<T> findOneRecordById(@PathVariable("id")Long id) {
        ExecuteResult<T> executeResult = new ExecuteResult<>();
        T recordList = service.findOneRecordById(id);
        executeResult.setResult(recordList);
        return executeResult;
    }

    /**
     * 保存数据
     * @param t
     * @return
     */
    @RequestMapping(value = "/record", method = RequestMethod.POST)
    protected ExecuteResult<T> addEditRecord(T t) {
        return service.addEditRecord(t);
    }

    /**
     * 删除数据
     * @param id
     * @return
     */
    @RequestMapping(value = "/record/{id:^\\d+$}", method = RequestMethod.DELETE)
    protected ExecuteResult<T> deleteRecord(@PathVariable("id")Long id) {
        return service.deletedRecord(id);
    }
}
