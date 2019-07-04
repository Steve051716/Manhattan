package com.gyh.manhattan.controller.api;

import com.alibaba.fastjson.JSON;
import com.gyh.manhattan.common.ConstParam;
import com.gyh.manhattan.common.ExecuteResult;
import com.gyh.manhattan.domain.UserInfo;
import com.gyh.manhattan.service.UserInfoService;
import com.gyh.manhattan.utils.IdUtil;
import com.gyh.manhattan.utils.RedisUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gao-yh
 */
@RestController
@RequestMapping(value = "/api")
public class UserApiController {

    private static final Logger LOG = LoggerFactory.getLogger(UserApiController.class);
    @Inject
    private UserInfoService userInfoService;
    @Inject
    private RedisUtil redisUtil;


    @ApiOperation(value="登陆校验", notes="根据用户、密码是否存在")
    @ApiImplicitParam(name = "request", value = "请求", required = true, dataType = "HttpServletRequest", paramType = "body")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request) throws Exception {
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
        String snowflakeId = IdUtil.getRandomIdToString();
        redisUtil.set(session.getId(), snowflakeId, 60L*30);
        result.setStatus(ConstParam.STATUS_SUCCESS);
        result.setRedirectUrl("/index");
        return JSON.toJSONString(result);
    }
}
