package com.gyh.manhattan.service.impl;

import com.gyh.manhattan.common.ConstParam;
import com.gyh.manhattan.common.ExecuteResult;
import com.gyh.manhattan.dao.SysMessageDAO;
import com.gyh.manhattan.model.SysMessageModel;
import com.gyh.manhattan.service.MessageService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

/**
 * @author gaoyuhang
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Inject
    private SysMessageDAO sysMessageDAO;
    /**
     * 根据参数获取数据集合
     *
     * @param param
     * @return
     */
    @Override
    public List<SysMessageModel> findRecords(Map<String, Object> param) {
        return sysMessageDAO.findRecordsByParam(param);
    }

    /**
     * 根据参数获取单条数据
     *
     * @param id
     * @return
     */
    @Override
    public SysMessageModel findOneRecordById(Long id) {
        return null;
    }

    /**
     * 根据参数获取单条数据
     *
     * @param param
     * @return
     */
    @Override
    public SysMessageModel findOneRecordByParam(Map<String, Object> param) {
        return null;
    }

    /**
     * 保存数据
     *
     * @param sysMessageModel
     * @return
     */
    @Override
    public ExecuteResult<SysMessageModel> addEditRecord(SysMessageModel sysMessageModel) {
        ExecuteResult<SysMessageModel> executeResult = new ExecuteResult<>();
        if (sysMessageModel != null) {
            if (sysMessageModel.getId() != null) {
                // id存在则更新
                executeResult = this.updateRecord(sysMessageModel);
            }
            else {
                executeResult = this.saveRecord(sysMessageModel);
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
     * @param sysMessageModel
     */
    @Override
    public ExecuteResult<SysMessageModel> saveRecord(SysMessageModel sysMessageModel) {
        ExecuteResult<SysMessageModel> executeResult = new ExecuteResult<>();
        sysMessageDAO.save(sysMessageModel);
        executeResult.setStatus(ConstParam.STATUS_SUCCESS);
        executeResult.setResult(sysMessageModel);
        return executeResult;
    }

    /**
     * 更新数据
     *
     * @param sysMessageModel
     * @return
     */
    @Override
    public ExecuteResult<SysMessageModel> updateRecord(SysMessageModel sysMessageModel) {
        ExecuteResult<SysMessageModel> executeResult = new ExecuteResult<>();
        int count = sysMessageDAO.update(sysMessageModel);
        if (count > 0) {
            executeResult.setStatus(ConstParam.STATUS_SUCCESS);
            sysMessageModel = this.findOneRecordById(sysMessageModel.getId());
            executeResult.setResult(sysMessageModel);
        }
        else {
            executeResult.setStatus(ConstParam.STATUS_FAILED);
        }
        return executeResult;
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    @Override
    public ExecuteResult<SysMessageModel> deletedRecord(Long id) {
        if (id == null) {
            return new ExecuteResult<>(ConstParam.STATUS_FAILED, ConstParam.MESSAGE_DATA_ERROR, ConstParam.MESSAGE_DATA_ERROR, null);
        }
        int count = sysMessageDAO.delete(id);
        if (count > 0) {
            return new ExecuteResult<>(ConstParam.STATUS_SUCCESS, null, null, null);
        }
        return new ExecuteResult<>(ConstParam.STATUS_FAILED, ConstParam.MESSAGE_DATA_ERROR, ConstParam.MESSAGE_DATA_ERROR, null);
    }


}
