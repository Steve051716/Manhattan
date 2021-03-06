package com.gyh.manhattan.aop;

import com.gyh.manhattan.base.aop.AbstractGlobalExceptionHandle;
import com.gyh.manhattan.common.ConstParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * controller增强
 * 返回页面
 * @author gaoyuhang
 */
@ControllerAdvice(basePackages = "com.gyh.manhattan.controller.view")
public class GlobalViewExceptionHandler extends AbstractGlobalExceptionHandle {

    private Logger LOG = LoggerFactory.getLogger(GlobalViewExceptionHandler.class);

    /**
     *
     * @param model
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public String defaultErrorHandler(Model model, Exception e) {
        model.addAttribute("result", super.defaultErrorHandler(e));
        return ConstParam.DEFAULT_ERROR_VIEW;
    }
}