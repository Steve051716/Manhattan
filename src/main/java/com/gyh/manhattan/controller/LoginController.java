package com.gyh.manhattan.controller;

import com.alibaba.fastjson.JSON;
import com.gyh.manhattan.common.ConstParam;
import com.gyh.manhattan.common.ExecuteResult;
import com.gyh.manhattan.domain.UserInfo;
import com.gyh.manhattan.service.UserInfoService;
import com.gyh.manhattan.utils.IdUtil;
import com.gyh.manhattan.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author gao-yh
 */
@Controller
public class LoginController {

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);
    @Inject
    private UserInfoService userInfoService;
    @Inject
    private RedisUtil redisUtil;

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
        HttpSession session = request.getSession();
        session.setAttribute(ConstParam.GLOBAL_SESSION_ATTRIBUTE_USER_ID, userInfo.getId());
        session.setAttribute(ConstParam.GLOBAL_SESSION_ATTRIBUTE_USER_NAME, userInfo.getName());
        LOG.info(session.getId());

        String snowflakeId = IdUtil.getRandomIdToString();
        LOG.info(snowflakeId);
        redisUtil.set(session.getId(), snowflakeId, 60L*30);
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
