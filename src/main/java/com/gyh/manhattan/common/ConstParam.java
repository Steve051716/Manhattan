package com.gyh.manhattan.common;

/**
 * @author gao-yh
 */
public class ConstParam {

    public static final String GLOBAL_SESSION_ATTRIBUTE_USER_ID = "loginUserId";
    public static final String GLOBAL_SESSION_ATTRIBUTE_USER_NAME = "loginUserName";

    public static final String MESSAGE_LOGIN_FAILED = "账号或密码不正确。";
    public static final String MESSAGE_LOGIN_INFO_EMPTY = "账号或密码不能为空。";

    public static final String STATUS_SUCCESS = "000000";
    public static final String STATUS_FAILED = "000001";
    public static final String STATUS_SYSTEM_EXCEPTION = "000002";

    // 定义错误显示页，error.html
    public static final String DEFAULT_ERROR_VIEW = "/error";
}
