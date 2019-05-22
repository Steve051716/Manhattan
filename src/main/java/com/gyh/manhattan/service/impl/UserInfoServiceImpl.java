package com.gyh.manhattan.service.impl;

import com.gyh.manhattan.dao.UserInfoDAO;
import com.gyh.manhattan.domain.UserInfo;
import com.gyh.manhattan.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

/**
 * @author gao-yh
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Inject
    private UserInfoDAO userInfoDAO;

    /**
     * 根据参数获取数据集合
     *
     * @param param
     * @return
     */
    @Override
    public List<UserInfo> findRecords(Map<String, Object> param) {
        return null;
    }

    /**
     * 根据参数获取单条数据
     *
     * @param id
     * @return
     */
    @Override
    public UserInfo findOneRecordById(Long id) {
        return null;
    }

    @Override
    public UserInfo findOneRecordByParam(Map<String, Object> param) {
        return userInfoDAO.findOneRecordByParam(param);
    }
}
