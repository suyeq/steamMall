package com.example.steam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-04-18
 * @time: 22:18
 */
@Controller
public class TemplateController {

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

}
