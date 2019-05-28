package com.example.steam.controller;

import com.example.steam.dao.GameDao;
import com.example.steam.entity.User;
import com.example.steam.service.GameService;
import com.example.steam.service.ImageService;
import com.example.steam.service.TypeService;
import com.example.steam.vo.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
public class PageController {

    Logger log= LoggerFactory.getLogger(PageController.class);

    @Autowired
    TypeService typeService;
    @Autowired
    GameService gameService;
    @Autowired
    ImageService imageService;




    @RequestMapping("/class/{typeName}")
    public String classify(LoginUser loginUser,
                           @PathVariable("typeName")String typeName,
                           Model model){
        log.error(typeName);
        model.addAttribute("typeName",typeName);
        model.addAttribute("user",loginUser);
        return "classlist";
    }

    @RequestMapping("/detail/{gameId}")
    public String detail(LoginUser loginUser,
                         @PathVariable("gameId")long gameId,
                         Model model){
        model.addAttribute("gameId",gameId);
        model.addAttribute("user",loginUser);
        return "gamedetail";
    }

    @RequestMapping("/cart")
    public String cart(LoginUser loginUser,
                       Model model){
        model.addAttribute("user",loginUser);
        return "shoppingcart";
    }
}
