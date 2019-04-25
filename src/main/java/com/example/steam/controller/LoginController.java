package com.example.steam.controller;

import com.alibaba.fastjson.JSON;
import com.example.steam.entity.User;
import com.example.steam.service.UserService;
import com.example.steam.utils.Md5PasswordConver;
import com.example.steam.utils.ResultMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-04-25
 * @time: 20:04
 */
@Controller
public class LoginController {

    @Autowired
    UserService userService;

    Logger log= LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @ResponseBody
    @RequestMapping("/userVerification")
    public String userLogin(@RequestParam("email")String email,
                            @RequestParam("password")String password){

        User user=userService.findByEmail(email);
        if (user == null){
            return JSON.toJSONString(ResultMsg.NO_EMAIL);
        }
        String finalPass= Md5PasswordConver.secondMd5(password,user.getSalt());
        if (!finalPass.equals(user.getPassword())){
            return JSON.toJSONString(ResultMsg.PASS_ERROR);
        }
        log.error(email+" "+password);
        //log.error(JSON.toJSONString(ResultMsg.LOGIN_SUCCESS));
        return JSON.toJSONString(ResultMsg.LOGIN_SUCCESS);
    }

    @RequestMapping("/register")
    public String register(){
        return "register";
    }
}
