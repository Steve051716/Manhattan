package com.gyh.manhattan.base.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gyh.manhattan.base.annotation.RequestCallBack;
import com.gyh.manhattan.base.form.BaseFormModel;
import com.gyh.manhattan.base.service.BaseService;
import com.gyh.manhattan.common.ConstParam;
import com.gyh.manhattan.common.ExecuteResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.util.List;

/**
 * 抽象controller基类
 * @author gaoyuhang
 */
public abstract class AbstractBaseController<T> {

    @Inject
    protected BaseService<T> service;

    /**
     * 分页查看多条数据
     * @param baseFormModel
     * @return
     */
    @RequestMapping(value = "/listPage", method = RequestMethod.GET)
    @RequestCallBack(type = ConstParam.REQUEST_CALL_BACK_TYPE_API)
    protected ExecuteResult<T> findRecordsByPage(BaseFormModel<T> baseFormModel) {
        ExecuteResult<T> executeResult = new ExecuteResult<>();
        PageHelper.startPage(baseFormModel.getPageNum(), baseFormModel.getPageSize());
        executeResult.setPageInfo(new PageInfo<>(this.findRecords(baseFormModel)));
        return executeResult;
    }

    /**
     * 查看多条数据
     * @param baseFormModel
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @RequestCallBack(type = ConstParam.REQUEST_CALL_BACK_TYPE_API)
    protected ExecuteResult<T> findRecordsByList(BaseFormModel<T> baseFormModel) {
        ExecuteResult<T> executeResult = new ExecuteResult<>();
        executeResult.setResultList(this.findRecords(baseFormModel));
        return executeResult;
    }

    /**
     *
     * @param baseFormModel
     * @return
     */
    private List<T> findRecords(BaseFormModel<T> baseFormModel) {
        return service.findRecords(null);
    }

    /**
     * 查看单条数据
     * @param id
     * @return
     */
    @RequestMapping(value = "/record/{id:^\\d+$}", method = RequestMethod.GET)
    @RequestCallBack(type = ConstParam.REQUEST_CALL_BACK_TYPE_API)
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
    @RequestCallBack(type = ConstParam.REQUEST_CALL_BACK_TYPE_API)
    protected ExecuteResult<T> addEditRecord(T t) {
        return service.addEditRecord(t);
    }

    /**
     * 删除数据
     * @param id
     * @return
     */
    @RequestMapping(value = "/record/{id:^\\d+$}", method = RequestMethod.DELETE)
    @RequestCallBack(type = ConstParam.REQUEST_CALL_BACK_TYPE_API)
    protected ExecuteResult<T> deleteRecord(@PathVariable("id")Long id) {
        return service.deletedRecord(id);
    }
}
