package com.gyh.manhattan.config.interceptor;

import com.gyh.manhattan.utils.IdUtil;
import com.gyh.manhattan.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author gao-yh
 */
public class LoginInterceptor implements HandlerInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(LoginInterceptor.class);
    @Inject
    private RedisUtil redisUtil;
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        HttpSession session = request.getSession();
        String sessionId = null;
        LOG.info(request.getRequestURI());
        if (session != null)  {
            sessionId = session.getId();
            if (!redisUtil.hasKey(sessionId)) {
                response.sendRedirect("/login");
                LOG.info("session已超时");
                return false;
            }
        }
        else {
            response.sendRedirect("/login");
            LOG.info("请先登录");
            return false;

        }
        // 重置session时间
        String snowflakeId = IdUtil.getRandomIdToString();
        redisUtil.set(sessionId, snowflakeId, 60L*30);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
