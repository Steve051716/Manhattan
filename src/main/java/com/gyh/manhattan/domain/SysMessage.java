package com.gyh.manhattan.domain;

import com.gyh.manhattan.base.domain.BaseEntityBean;

public class SysMessage extends BaseEntityBean {

    private String title;

    private String content;

    private Long userId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}