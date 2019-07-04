package com.gyh.manhattan.controller.view;

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
import javax.servlet.http.HttpServletResponse;

/**
 * @author
 */
@Controller
public class IndexController {

    private static final Logger LOG = LoggerFactory.getLogger(IndexController.class);

    @Inject
    private UserInfoService userInfoService;

    @ApiIgnore
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model, HttpServletResponse response) {
        return "/login";
    }

    @ApiIgnore
    @RequestMapping(value = "logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getSession().invalidate();
        response.sendRedirect("/login");
        return null;
    }

    @ApiIgnore
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model) {
        String userId = request.getSession().getAttribute(ConstParam.GLOBAL_SESSION_ATTRIBUTE_USER_ID) + "";
        String userName = request.getSession().getAttribute(ConstParam.GLOBAL_SESSION_ATTRIBUTE_USER_NAME) + "";
        UserInfo userInfo = userInfoService.findOneRecordById(Long.valueOf(userId));
        model.addAttribute("userId", userId);
        model.addAttribute("userName", userName);
        model.addAttribute("remark", userInfo.getRemark());
        return "/layout/index";
    }

    @ApiIgnore
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String showDashboard(HttpServletRequest request, Model model) {
        return "/dashboard";
    }
}