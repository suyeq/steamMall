package com.example.steam.controller;

import com.alibaba.fastjson.JSON;
import com.example.steam.service.UserService;
import com.example.steam.utils.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * -----------
 * 穷则独善其身
 * 达则兼济天下
 * -----------
 *
 * @author: 苍术
 * @date: 2019-06-20
 * @time: 15:01
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping("/user/{email}")
    public String findUserByEmail(@PathVariable("email") String email){
        return JSON.toJSONString(ResultMsg.SUCCESS(userService.findUserVoByEmail(email)));
    }
}
