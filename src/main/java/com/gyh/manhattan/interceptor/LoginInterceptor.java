package com.gyh.manhattan.interceptor;

import com.gyh.manhattan.common.ConstParam;
import com.gyh.manhattan.utils.IdUtil;
import com.gyh.manhattan.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
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
                             HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        String seesionId = null;
        LOG.info(request.getRequestURI());
        if (session != null)  {
            seesionId = session.getId();
            if (!redisUtil.hasKey(seesionId)) {
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
        LOG.info(snowflakeId);
        redisUtil.set(seesionId, snowflakeId, 60L*30);
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
