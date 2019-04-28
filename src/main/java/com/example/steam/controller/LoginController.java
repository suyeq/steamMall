package com.example.steam.controller;

import com.alibaba.fastjson.JSON;
import com.example.steam.entity.User;
import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.UserKey;
import com.example.steam.service.ImageService;
import com.example.steam.service.UserService;
import com.example.steam.utils.Md5PasswordConver;
import com.example.steam.utils.ResultMsg;
import com.example.steam.utils.UUIDUntil;
import com.example.steam.vo.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
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

    Logger log= LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/")
    public String index(User user, Model model){
        LoginUser loginUser=userService.converViewLoginUser(user);
        model.addAttribute("user",loginUser);
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @ResponseBody
    @RequestMapping("/userVerification")
    public String userLogin(@RequestParam("email")String email,
                            @RequestParam("password")String password,
                            HttpServletResponse response,
                            HttpServletRequest request){

        return JSON.toJSONString(userService.handleLogin(email,password,request,response));
    }

    @RequestMapping("/register")
    public String register(){
        return "register";
    }



}
