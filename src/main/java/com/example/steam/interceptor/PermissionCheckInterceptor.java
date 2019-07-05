package com.example.steam.interceptor;

import com.example.steam.entity.User;
import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.UserKey;
import com.example.steam.utils.AdminUserHoleUtil;
import com.example.steam.utils.StaticField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: 苍术
 * @date: 2019-06-26
 * @time: 20:44
 */
@Component
public class PermissionCheckInterceptor implements HandlerInterceptor {

    Logger log= LoggerFactory.getLogger(PermissionCheckInterceptor.class);

    @Autowired
    RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String email=(String) request.getSession().getAttribute(StaticField.EMAIL_KEY);
        if (request.getRequestURI().equals("/admin/login") || request.getRequestURI().equals("/admin/userVerification")){
            return true;
        }
        if (AdminUserHoleUtil.getUser(email)!=null){
            return true;
        }
        log.info("管理员为空");
        response.sendRedirect("/admin/login");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }

}
