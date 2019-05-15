package com.gyh.manhattan.service.impl;

import com.gyh.manhattan.dao.UserInfoDAO;
import com.gyh.manhattan.domain.UserInfo;
import com.gyh.manhattan.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Map;

/**
 * @author gao-yh
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Inject
    private UserInfoDAO userInfoDAO;

    @Override
    public UserInfo findOneRecordByParam(Map<String, Object> param) {
        return userInfoDAO.findOneRecordByParam(param);
    }
}
