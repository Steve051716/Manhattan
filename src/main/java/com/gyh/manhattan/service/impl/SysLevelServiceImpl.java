package com.gyh.manhattan.service.impl;

import com.gyh.manhattan.dao.SysLevelDAO;
import com.gyh.manhattan.domain.SysLevel;
import com.gyh.manhattan.service.SysLevelService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Service
public class SysLevelServiceImpl implements SysLevelService {

    @Inject
    private SysLevelDAO sysLevelDAO;
    /**
     * 根据参数获取数据集合
     *
     * @param param
     * @return
     */
    @Override
    public List<SysLevel> findRecords(Map<String, Object> param) {
        return null;
    }

    /**
     * 根据参数获取单条数据
     *
     * @param id
     * @return
     */
    @Override
    public SysLevel findOneRecordById(Long id) {
        return null;
    }

    /**
     * 根据参数获取单条数据
     *
     * @param param
     * @return
     */
    @Override
    public SysLevel findOneRecordByParam(Map<String, Object> param) {
        return sysLevelDAO.findOneRecordByParam(param);
    }

    /**
     * 保存数据
     *
     * @param sysLevel
     */
    @Override
    public void saveRecord(SysLevel sysLevel) {

    }

    /**
     * 更新数据
     *
     * @param sysLevel
     * @return
     */
    @Override
    public int updateRecord(SysLevel sysLevel) {
        return 0;
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    @Override
    public int deletedRecord(Long id) {
        return 0;
    }
}
