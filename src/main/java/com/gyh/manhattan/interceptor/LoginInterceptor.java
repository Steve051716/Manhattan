package com.gyh.manhattan.interceptor;

import com.gyh.manhattan.common.ConstParam;
import com.gyh.manhattan.domain.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author gao-yh
 */
public class LoginInterceptor implements HandlerInterceptor {
    private Logger LOG = LoggerFactory.getLogger(LoginInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        String userName = (String)request.getSession().getAttribute(ConstParam.GLOBAL_SESSION_ATTRIBUTE_USER_NAME);
        LOG.info(request.getRequestURI());
        if (StringUtils.isBlank(userName))  {
            response.sendRedirect("/login");
            LOG.info("请先登录");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        LOG.info("postHandle...");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LOG.info("afterCompletion...");
    }
}
