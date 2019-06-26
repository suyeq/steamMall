package com.example.steam.interceptor;

import com.example.steam.utils.AdminUserHoleUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

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

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getRequestURI().equals("/admin/login") || request.getRequestURI().equals("/admin/userVerification")){
            return true;
        }
        if (AdminUserHoleUtil.getUser()!=null){
            return true;
        }
//        log.error("管理员为空");
//        response.sendRedirect("/admin/login");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }

}
