package com.gyh.manhattan.base.dao;

import java.util.List;
import java.util.Map;

/**
 * DAO基类
 * @author gaoyuhang
 * @param <T>
 * @param <Long>
 */
public interface BaseDAO<T, Long> {

    /**
     * 根据id查找单条数据
     * @param id
     * @return
     */
    T findOneRecordById(Long id);

    /**
     * 根据参数查找单条数据
     * @param param
     * @return
     */
    T findOneRecordByParam(Map<String, Object> param);

    /**
     * 根据参数查找数据集合
     * @param param
     * @return
     */
    List<T> findRecordsByParam(Map<String, Object> param);

    /**
     * 保存
     * @param t
     */
    int save(T t);

    /**
     * 更新
     * @param t
     * @return
     */
    int update(T t);

    /**
     * 删除
     * @param id
     * @return
     */
    int delete(Long id);
}
