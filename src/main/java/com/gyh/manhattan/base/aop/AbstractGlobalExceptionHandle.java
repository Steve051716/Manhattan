package com.gyh.manhattan.base.aop;

import com.gyh.manhattan.common.ConstParam;
import com.gyh.manhattan.common.ExecuteResult;
import com.gyh.manhattan.utils.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 抽象controller增强
 * @author gaoyuhang
 */
public abstract class AbstractGlobalExceptionHandle {

    private Logger LOG = LoggerFactory.getLogger(AbstractGlobalExceptionHandle.class);

    /**
     * 异常统一处理
     * @param e
     * @return
     */
    protected ExecuteResult defaultErrorHandler(Throwable e) {
        String errorMessage = e.toString();
        String errorFullMessage = CommonUtil.getExceptionMessage(e);
        LOG.error(errorMessage);
        ExecuteResult result = new ExecuteResult();
        result.setStatus(ConstParam.STATUS_SYSTEM_EXCEPTION);
        result.setMessage(errorMessage);
        result.setFullErrorMessage(errorFullMessage);
        return result;
    }
}
