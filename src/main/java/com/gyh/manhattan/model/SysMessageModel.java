package com.gyh.manhattan.model;

import com.gyh.manhattan.domain.SysMessage;

/**
 * @author gaoyuhang
 */
public class SysMessageModel extends SysMessage {

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
