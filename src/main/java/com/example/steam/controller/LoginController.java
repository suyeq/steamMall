package com.example.steam.controller;

import com.alibaba.fastjson.JSON;
import com.example.steam.entity.User;
import com.example.steam.mq.Event;
import com.example.steam.mq.EventType;
import com.example.steam.mq.MQProducer;
import com.example.steam.redis.RedisService;
import com.example.steam.service.ImageService;
import com.example.steam.service.UserService;
import com.example.steam.utils.ResultMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-04-25
 * @time: 20:04
 */
@Controller
public class LoginController {

    private final static String COOKIE_ID="token";

    @Value("${server.servlet.session.cookie.max-age}")
    int cookieMaxAge;

    @Autowired
    UserService userService;
    @Autowired
    RedisService redisService;
    @Autowired
    ImageService imageService;
    @Autowired
    MQProducer mqProducer;

    Logger log= LoggerFactory.getLogger(LoginController.class);

    /**
     * 登录验证
     * @param email
     * @param password
     * @param response
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/userVerification")
    public String userLogin(@RequestParam("email")String email,
                            @RequestParam("password")String password,
                            HttpServletResponse response,
                            HttpServletRequest request){

        return JSON.toJSONString(userService.handleLogin(email,password,request,response));
    }

    @ResponseBody
    @RequestMapping("/registerVerification")
    public String userRegister(@RequestParam("email")String email,
                               @RequestParam("password")String password,
                               @RequestParam("code")String code){
        log.info("email:"+email+","+"pass:"+password+","+"code:"+code);
        return JSON.toJSONString(userService.handleRegister(email,password,code));
    }

    /**
     * 发送验证码邮件
     * @return
     */
    @ResponseBody
    @RequestMapping("/verificationCode")
    public String sendVerificationCode(@RequestParam("email")String email){
        log.info(email);
        mqProducer.productEvent(new Event(EventType.SEND_EMAIL_VERIFICATION_CODE).
                setEtrMsg(Event.EMAIL,email));
        return JSON.toJSONString(ResultMsg.SUCCESS);
    }

    @ResponseBody
    @RequestMapping("/logout")
    public String logout(@RequestParam("email")String email){
        return JSON.toJSONString(ResultMsg.SUCCESS(userService.handleLogout(email)));
    }

    @ResponseBody
    @RequestMapping("/findpassword")
    public String findPassword(@RequestParam("email")String email){
        return JSON.toJSONString(userService.updateFindPassword(email));
    }

//    @ResponseBody
//    @RequestMapping("/addUser")
//    public String addUser(){
//        User user=new User("111","111","111","111");
//        return JSON.toJSONString(userService.addUser(user));
//    }



}
