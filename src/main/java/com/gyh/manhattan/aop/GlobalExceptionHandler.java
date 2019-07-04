package com.gyh.manhattan.aop;

import com.gyh.manhattan.common.ConstParam;
import com.gyh.manhattan.common.ExecuteResult;
import com.gyh.manhattan.utils.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * 作为一个控制层的切面处理
 * @author
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    // 定义错误显示页，error.html
    public static final String DEFAULT_ERROR_VIEW = "/error";

    /**
     *
     * @param request
     * @param e
     * @return
     */
    /*@ExceptionHandler(Exception.class)
    @ResponseBody
    public String defaultErrorHandler2Json(HttpServletRequest request, Exception e) {
        LOG.error(e.toString());
        ExecuteResult result = new ExecuteResult();
        result.setStatus(ConstParam.STATUS_SYSTEM_EXCEPTION);
        result.setMessage(e.toString());
        return JSON.toJSONString(result);
    }*/

    /**
     *
     * @param model
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public String defaultErrorHandler2Page(Model model, Exception e) {
        String errorMessage = CommonUtil.getExceptionMessage(e);
        // LOG.error(errorMessage);
        ExecuteResult result = new ExecuteResult();
        result.setStatus(ConstParam.STATUS_SYSTEM_EXCEPTION);
        result.setMessage(e.toString());
        result.setFullErrorMessage(errorMessage);
        model.addAttribute("result", result);
        return "/error";
    }
}