package com.gyh.manhattan.base.service.impl;

import com.gyh.manhattan.base.dao.BaseDAO;
import com.gyh.manhattan.base.domain.BaseEntityBean;
import com.gyh.manhattan.base.service.BaseService;
import com.gyh.manhattan.common.ConstParam;
import com.gyh.manhattan.common.ExecuteResult;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

/**
 * @author gaoyuhang
 * @param <T>
 */
public class BaseServiceImpl<T extends BaseEntityBean> implements BaseService {

    @Inject
    private BaseDAO<T, Long> baseDAO;


    /**
     * 根据参数获取数据集合
     *
     * @param param
     * @return
     */
    @Override
    public List<T> findRecords(Map param) {
        return baseDAO.findRecordsByParam(param);
    }

    /**
     * 根据参数获取单条数据
     *
     * @param id
     * @return
     */
    @Override
    public T findOneRecordById(Long id) {
        return null;
    }

    /**
     * 保存数据
     *
     * @param o
     * @return
     */
    @Override
    @Transactional
    public ExecuteResult<T> addEditRecord(Object o) {
        ExecuteResult<T> executeResult = new ExecuteResult<>();
        if (o != null) {
            T t = (T)o;
            if (t.getId() != null) {
                // id存在则更新
                executeResult = this.updateRecord(t);
            }
            else {
                executeResult = this.saveRecord(t);
            }
        }
        else {
            executeResult.setStatus(ConstParam.STATUS_FAILED);
        }
        return executeResult;
    }

    /**
     * 保存数据
     *
     * @param o
     */
    @Override
    @Transactional
    public ExecuteResult<T> saveRecord(Object o) {
        ExecuteResult<T> executeResult = new ExecuteResult<>();
        if (o != null) {
            T t = (T)o;
            baseDAO.save(t);
            executeResult.setStatus(ConstParam.STATUS_SUCCESS);
            executeResult.setResult(t);
            return executeResult;
        }
        executeResult.setStatus(ConstParam.STATUS_FAILED);
        return executeResult;
    }

    /**
     * 更新数据
     *
     * @param o
     * @return
     */
    @Override
    @Transactional
    public ExecuteResult<T> updateRecord(Object o) {
        ExecuteResult<T> executeResult = new ExecuteResult<>();
        if (o != null) {
            T t = (T)o;
            int count = baseDAO.update(t);
            if (count > 0) {
                executeResult.setStatus(ConstParam.STATUS_SUCCESS);
                executeResult.setResult(t);
                return executeResult;
            }
        }
        executeResult.setStatus(ConstParam.STATUS_FAILED);
        return executeResult;
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    @Override
    @Transactional
    public ExecuteResult deletedRecord(Long id) {
        if (id == null) {
            return new ExecuteResult<>(ConstParam.STATUS_FAILED, ConstParam.MESSAGE_DATA_ERROR, ConstParam.MESSAGE_DATA_ERROR, null);
        }
        int count = baseDAO.delete(id);
        if (count > 0) {
            return new ExecuteResult<>(ConstParam.STATUS_SUCCESS, null, null, null);
        }
        return new ExecuteResult<>(ConstParam.STATUS_FAILED, ConstParam.MESSAGE_DATA_ERROR, ConstParam.MESSAGE_DATA_ERROR, null);
    }

    /**
     * 根据参数获取单条数据
     *
     * @param param
     * @return
     */
    @Override
    public Object findOneRecordByParam(Map param) {
        return null;
    }
}
