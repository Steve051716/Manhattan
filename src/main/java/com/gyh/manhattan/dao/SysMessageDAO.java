package com.gyh.manhattan.dao;

import com.gyh.manhattan.base.dao.BaseDAO;
import com.gyh.manhattan.model.SysMessageModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author gaoyuhang
 */
@Mapper
public interface SysMessageDAO extends BaseDAO<SysMessageModel, Long> {

}