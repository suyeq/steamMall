package com.example.steam;

import com.example.steam.entity.Type;
import com.example.steam.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-04-18
 * @time: 22:18
 */
@Controller
public class TemplateController {

    @Autowired
    TypeService typeService;
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    @RequestMapping("/class")
    public String classify(){
        return "classlist";
    }

    @RequestMapping("/detail")
    public String detail(){
        return "gamedetail";
    }

    @RequestMapping("/cart")
    public String cart(){
        return "shoppingcart";
    }

    @ResponseBody
    @RequestMapping("/test")
    public String test(){
        System.out.println(typeService.findAllTyp().toString());
        Type type=new Type();
        type.setTypeName("欢乐");
        System.out.println(typeService.addType(type));

        return "ok";
    }



}
