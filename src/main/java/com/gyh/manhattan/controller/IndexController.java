package com.gyh.manhattan.controller;

import com.gyh.manhattan.common.ConstParam;
import com.gyh.manhattan.domain.UserInfo;
import com.gyh.manhattan.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.annotations.ApiIgnore;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * @author
 */
@Controller
public class IndexController {

    private static final Logger LOG = LoggerFactory.getLogger(IndexController.class);

    @Inject
    private UserInfoService userInfoService;

    @ApiIgnore
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model) {
        String userId = request.getSession().getAttribute(ConstParam.GLOBAL_SESSION_ATTRIBUTE_USER_ID) + "";
        String userName = request.getSession().getAttribute(ConstParam.GLOBAL_SESSION_ATTRIBUTE_USER_NAME) + "";
        LOG.info("sessionId：" + request.getSession().getId());
        UserInfo userInfo = userInfoService.findOneRecordById(Long.valueOf(userId));
        model.addAttribute("userId", userId);
        model.addAttribute("userName", userName);
        model.addAttribute("remark", userInfo.getRemark());
        return "index";
    }
}
