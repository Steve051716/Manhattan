package com.gyh.manhattan.base.service;

import java.util.List;
import java.util.Map;

/**
 * Service基类
 * @author gao-yh
 * @param <T>
 */
public interface BaseService<T> {

    /**
     * 根据参数获取数据集合
     * @param param
     * @return
     */
    List<T> findRecords(Map<String, Object> param);

    /**
     * 根据参数获取单条数据
     * @param id
     * @return
     */
    T findOneRecordById(Long id);

    /**
     * 根据参数获取单条数据
     * @param param
     * @return
     */
    T findOneRecordByParam(Map<String, Object> param);
}
