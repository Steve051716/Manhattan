package com.gyh.manhattan.controller;

import com.alibaba.fastjson.JSON;
import com.gyh.manhattan.common.ConstParam;
import com.gyh.manhattan.common.ExecuteResult;
import com.gyh.manhattan.domain.UserInfo;
import com.gyh.manhattan.service.UserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gao-yh
 */
@Controller
public class LoginController {

    @Inject
    private UserInfoService userInfoService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model, HttpServletResponse response) {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(HttpServletRequest request, Model model, HttpServletResponse response) throws Exception {
        ExecuteResult result = new ExecuteResult();
        String name = request.getParameter("userName");
        String password = request.getParameter("userPassword");

        if (StringUtils.isBlank(name) || StringUtils.isBlank(password)) {
            result.setStatus(ConstParam.STATUS_FAILED);
            result.setMessage(ConstParam.MESSAGE_LOGIN_INFO_EMPTY);
            return JSON.toJSONString(result);
        }

        Map<String, Object> param = new HashMap<>(16);
        param.put("name", name);
        param.put("password", password);
        UserInfo userInfo = userInfoService.findOneRecordByParam(param);
        if (userInfo == null) {
            result.setStatus(ConstParam.STATUS_FAILED);
            result.setMessage(ConstParam.MESSAGE_LOGIN_FAILED);
            return JSON.toJSONString(result);
        }
        request.getSession().setAttribute(ConstParam.GLOBAL_SESSION_ATTRIBUTE_USER_NAME, userInfo.getName());
        result.setStatus(ConstParam.STATUS_SUCCESS);
        result.setRedirectUrl("/index");
        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getSession().invalidate();
        response.sendRedirect("/login");
        return null;
    }
}
