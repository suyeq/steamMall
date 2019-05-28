package com.example.steam.config;


import com.example.steam.entity.User;
import com.example.steam.service.UserService;
import com.example.steam.utils.StaticField;
import com.example.steam.vo.LoginUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * 自定义参数解析器，用来解析User对象参数
 * @author: Suyeq
 * @date: 2019-04-09
 * @time: 17:32
 */
@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    UserService userService;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class clazz=methodParameter.getParameterType();
        return clazz == LoginUser.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request=(HttpServletRequest)nativeWebRequest.getNativeRequest();
        HttpServletResponse response=(HttpServletResponse)nativeWebRequest.getNativeResponse();
        String cookieToken=getCookieValue(request, StaticField.COOKIE_KEY);
        return userService.getUserByToken(response,cookieToken);
    }

    private String getCookieValue(HttpServletRequest request, String cookie_token) {
        Cookie [] cookies=request.getCookies();
        if (cookies == null || cookies.length<=0){
            return null;
        }
        for (int i=0;i<cookies.length;i++){
            if (cookies[i].getName().equals(cookie_token)){
                return cookies[i].getValue();
            }
        }
        return null;
    }
}
