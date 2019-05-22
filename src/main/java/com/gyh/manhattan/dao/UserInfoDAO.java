package com.gyh.manhattan.dao;

import com.gyh.manhattan.base.dao.BaseDAO;
import com.gyh.manhattan.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author gao-yh
 */
@Mapper
public interface UserInfoDAO extends BaseDAO<UserInfo, Long> {

}