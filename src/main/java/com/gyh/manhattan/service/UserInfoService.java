package com.gyh.manhattan.service;

import com.gyh.manhattan.base.service.BaseService;
import com.gyh.manhattan.domain.UserInfo;

import java.util.Map;

/**
 * @author gao-yh
 */
public interface UserInfoService extends BaseService<UserInfo> {

    UserInfo findOneRecordByParam(Map<String, Object> param);
}
