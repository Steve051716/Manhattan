package com.gyh.manhattan.aop;

import com.alibaba.fastjson.JSON;
import com.gyh.manhattan.base.aop.AbstractGlobalExceptionHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * controller增强
 * 返回JSON格式
 * @author gaoyuhang
 */
@RestControllerAdvice(basePackages = "com.gyh.manhattan.controller.api")
public class GlobalApiExceptionHandle extends AbstractGlobalExceptionHandle {

    private Logger LOG = LoggerFactory.getLogger(GlobalApiExceptionHandle.class);
    /**
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String defaultErrorHandler(Exception e) {
        return JSON.toJSONString(super.defaultErrorHandler(e));
    }
}