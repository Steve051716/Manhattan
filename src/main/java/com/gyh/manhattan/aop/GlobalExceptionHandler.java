package com.gyh.manhattan.aop;

import com.alibaba.fastjson.JSON;
import com.gyh.manhattan.common.ConstParam;
import com.gyh.manhattan.common.ExecuteResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * 作为一个控制层的切面处理
 * @author
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    // 定义错误显示页，error.html
    public static final String DEFAULT_ERROR_VIEW = "error/error";

    /**
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String defaultErrorHandler(HttpServletRequest request, Exception e) {
        LOG.error(e.toString());
        ExecuteResult result = new ExecuteResult();
        result.setStatus(ConstParam.STATUS_SYSTEM_EXCEPTION);
        result.setMessage(e.toString());
        return JSON.toJSONString(result);
    }
}