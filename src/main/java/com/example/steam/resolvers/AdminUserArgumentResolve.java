package com.example.steam.resolvers;

import com.example.steam.entity.User;
import com.example.steam.utils.AdminUserHoleUtil;
import com.example.steam.utils.StaticField;
import com.example.steam.vo.AdminUser;
import com.example.steam.vo.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: 苍术
 * @date: 2019-07-05
 * @time: 9:41
 */
@Component
public class AdminUserArgumentResolve implements HandlerMethodArgumentResolver {

    Logger log= LoggerFactory.getLogger(AdminUserArgumentResolve.class);

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class clazz=methodParameter.getParameterType();
        return clazz == AdminUser.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request=(HttpServletRequest)nativeWebRequest.getNativeRequest();
       // HttpServletResponse response=(HttpServletResponse)nativeWebRequest.getNativeResponse();
        String email=(String) request.getSession().getAttribute(StaticField.EMAIL_KEY);
        User user=AdminUserHoleUtil.getUser(email);
        AdminUser adminUser=new AdminUser();
        adminUser.setId(user.getId());
        adminUser.setEmail(user.getEmail());
        adminUser.setNickName(user.getNickName());
        return adminUser;
    }
}
