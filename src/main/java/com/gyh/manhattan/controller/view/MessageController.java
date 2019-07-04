package com.gyh.manhattan.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author gaoyuhang
 */
@Controller
@RequestMapping(value = "/message")
public class MessageController {

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String index() {
        return "/list";
    }
}
