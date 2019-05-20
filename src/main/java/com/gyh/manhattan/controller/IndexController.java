package com.gyh.manhattan.controller;

import com.gyh.manhattan.common.ConstParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @author
 */
@Controller
public class IndexController {

    private static final Logger LOG = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model) {
        String userName = request.getSession().getAttribute(ConstParam.GLOBAL_SESSION_ATTRIBUTE_USER_NAME) + "";
        LOG.info("sessionId-------->" + request.getSession().getId());
        model.addAttribute("userName", userName);
        return "index";
    }
}
