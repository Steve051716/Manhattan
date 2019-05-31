package com.gyh.manhattan.dao;

import com.gyh.manhattan.base.dao.BaseDAO;
import com.gyh.manhattan.domain.SysLevel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysLevelDAO extends BaseDAO<SysLevel, Long> {
}