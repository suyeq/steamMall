package com.example.steam.service;

import com.alibaba.fastjson.JSON;
import com.example.steam.dao.UserDao;
import com.example.steam.entity.User;
import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.CookieKey;
import com.example.steam.redis.key.UserKey;
import com.example.steam.utils.Md5PasswordConver;
import com.example.steam.utils.ResultMsg;
import com.example.steam.utils.StaticField;
import com.example.steam.utils.UUIDUntil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-04-25
 * @time: 16:58
 */
@Service
public class UserService {

    Logger log= LoggerFactory.getLogger(UserService.class);

    @Value("${server.servlet.session.cookie.max-age}")
    int cookieMaxAge;

    @Autowired
    UserDao userDao;

    @Autowired
    RedisService redisService;

    @Autowired
    ApplicationContext applicationContext;

    public User findByEmail(String email){
        //User user=redisService.get(UserKey.USER_OBJECT,)
        return userDao.findByEmail(email);
    }

    /**
     * 通过token从缓存中获取user对象
     * @param response
     * @param cookieToken
     * @return
     */
    public User getUserByToken(HttpServletResponse response, String cookieToken) {
        if (cookieToken == null){
            return null;
        }
        User user=redisService.get(UserKey.COOKIE_ID,cookieToken,User.class);
        if (user != null){
            addCookie(response,cookieToken,user);
        }
        return user;
    }

    /**
     * 处理登录信息
     * @param email
     * @param password
     * @param request
     * @param response
     * @return
     */
    public ResultMsg handleLogin(String email, String password,HttpServletRequest request,HttpServletResponse response) {
        log.error(email+" "+password);
        if (redisService.get(CookieKey.EMAIL,email,String.class)!=null){
            return ResultMsg.HAD_Login;
        }
        /**
         * 此处必须有外部调用，内部调用不会走代理路线
         * 即AOP会失效
         */
        User user=((UserService)applicationContext.getBean("userService")).findByEmail(email);
        if (user == null){
            return ResultMsg.NO_EMAIL;
        }
        String finalPass= Md5PasswordConver.secondMd5(password,user.getSalt());
        if (!finalPass.equals(user.getPassword())){
            return ResultMsg.PASS_ERROR;
        }
        cookieIsNullAndCreate(request,response,user);
        return ResultMsg.LOGIN_SUCCESS;
    }

    private void cookieIsNullAndCreate(HttpServletRequest request, HttpServletResponse response, User user){
        Cookie cookie=findCookie(request);
        String cookieId=null;
        if (cookie == null){
            cookieId=UUIDUntil.randomUUID();
        }
        addCookie(response,cookieId,user);
    }

    private void addCookie(HttpServletResponse response,String cookieId,User user){
        Cookie cookie=new Cookie(StaticField.COOKIE_KEY,cookieId);
        cookie.setMaxAge(cookieMaxAge);
        response.addCookie(cookie);
        UserKey.COOKIE_ID.setExpiredTime(cookieMaxAge);
        redisService.set(UserKey.COOKIE_ID,cookieId,user);
        CookieKey.EMAIL.setExpiredTime(cookieMaxAge);
        redisService.set(CookieKey.EMAIL,user.getEmail(),cookieId);
    }

    private Cookie findCookie(HttpServletRequest request){
        Cookie[] cookies=request.getCookies();
        for (int i=0;i<cookies.length;i++){
            if (cookies[i].getName().equals(StaticField.COOKIE_KEY)){
                return cookies[i];
            }
        }
        return null;
    }
}
