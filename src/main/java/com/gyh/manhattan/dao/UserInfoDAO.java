package com.gyh.manhattan.dao;

import com.gyh.manhattan.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserInfoDAO {

    int delete(Integer id);

    int save(UserInfo record);

    UserInfo findOneRecordByParam(Map<String, Object> param);

    int update(UserInfo record);
}