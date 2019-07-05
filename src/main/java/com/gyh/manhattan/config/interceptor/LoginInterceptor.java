package com.gyh.manhattan.config.interceptor;

import com.alibaba.fastjson.JSON;
import com.gyh.manhattan.base.annotation.RequestCallBack;
import com.gyh.manhattan.common.ConstParam;
import com.gyh.manhattan.common.ExecuteResult;
import com.gyh.manhattan.utils.IdUtil;
import com.gyh.manhattan.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
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
        boolean isLogin = false;
        if (session != null)  {
            sessionId = session.getId();
            if (!redisUtil.hasKey(sessionId)) {
                isLogin = true;
                LOG.error("session已超时");
            }
        }
        else {
            isLogin = true;
            LOG.error("请先登录");
        }
        if (isLogin) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            LOG.info(handlerMethod.getMethod().getName());
            RequestCallBack requestCallBack = handlerMethod.getMethod().getAnnotation(RequestCallBack.class);
            if (ConstParam.REQUEST_CALL_BACK_TYPE_API.equals(requestCallBack.type())) {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().print(JSON.toJSON(new ExecuteResult<>(
                        ConstParam.STATUS_NO_LOGIN,
                        ConstParam.MESSAGE_NO_LOGIN,
                        ConstParam.MESSAGE_NO_LOGIN, null)));
            }
            else {
                response.sendRedirect("/login");
            }
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
