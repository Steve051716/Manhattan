package com.gyh.manhattan.controller.view;

import com.gyh.manhattan.base.annotation.RequestCallBack;
import com.gyh.manhattan.common.ConstParam;
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
    @RequestCallBack(type = ConstParam.REQUEST_CALL_BACK_TYPE_VIEW)
    public String index() {
        return "/list";
    }
}
