package com.gyh.manhattan.service;

import com.gyh.manhattan.domain.UserInfo;

import java.util.Map;

public interface UserInfoService {

    UserInfo findOneRecordByParam(Map<String, Object> param);
}
