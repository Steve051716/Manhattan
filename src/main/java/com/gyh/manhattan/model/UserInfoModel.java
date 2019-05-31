package com.gyh.manhattan.model;

import com.gyh.manhattan.domain.UserInfo;

public class UserInfoModel extends UserInfo {
    private int age;
    private String linkName;
    private String level;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
