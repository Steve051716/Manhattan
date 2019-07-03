package com.gyh.manhattan.controller;

import com.gyh.manhattan.domain.UserInfo;
import com.gyh.manhattan.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * @author gaoyuhang
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    @Inject
    private UserInfoService userInfoService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String save(HttpServletRequest request, Model model) {
        String id = request.getParameter("userId");
        String remark = request.getParameter("remark");
        LOG.info("id: " + id);
        LOG.info("remark: " + remark);
        UserInfo userInfo = new UserInfo();
        userInfo.setId(Long.valueOf(id));
        userInfo.setRemark(remark);
        userInfoService.updateRecord(userInfo);
        return "index";
    }
}
