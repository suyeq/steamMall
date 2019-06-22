package com.example.steam.controller;

import com.example.steam.dao.GameDao;
import com.example.steam.entity.User;
import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.SpikeGameKey;
import com.example.steam.service.*;
import com.example.steam.vo.LoginUser;
import com.example.steam.vo.SpikeGameDetail;
import com.example.steam.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @Autowired
    UserGameService userGameService;
    @Autowired
    RedisService redisService;
    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String index(LoginUser loginUser, Model model){
        model.addAttribute("user",loginUser);
        //
        // log.error(loginUser.getId()+"");
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

    @RequestMapping("/classification/{typeName}")
    public String classify(LoginUser loginUser,
                           @PathVariable("typeName")String typeName,
                           Model model){
        log.info(typeName);
        model.addAttribute("typeName",typeName);
        model.addAttribute("user",loginUser);
        return "classlist";
    }

    @RequestMapping("/detail/{gameId}")
    public String detail(LoginUser loginUser,
                         @PathVariable("gameId")long gameId,
                         Model model){
        Boolean contains=null;
        if (loginUser!=null){
            log.error(loginUser.toString());
            contains=userGameService.isContains(loginUser.getEmail(),gameId);
        }
        SpikeGameDetail spikeGameDetail=redisService.get(SpikeGameKey.SPIKE_ID,gameId+"",SpikeGameDetail.class);
        model.addAttribute("spike",spikeGameDetail);
        model.addAttribute("gameId",gameId);
        model.addAttribute("user",loginUser);
        model.addAttribute("contains",contains);
        return "gamedetail";
    }

    @RequestMapping("/cart")
    public String cart(LoginUser loginUser,
                       Model model){
        model.addAttribute("user",loginUser);
        return "shoppingcart";
    }

    @RequestMapping("/search")
    public String searchResult(LoginUser loginUser,
                               @RequestParam("content")String content,
                               Model model){
        model.addAttribute("content",content);
        model.addAttribute("user",loginUser);
        return "searchresult";
    }

    @RequestMapping("/personalcenter")
    public String personalCenter(LoginUser loginUser,
                                 Model model){
        model.addAttribute("user",loginUser);
        return "personalcenter";
    }

    @RequestMapping("/editpersonal")
    public String editPersonal(LoginUser loginUser,
                               Model model){
        model.addAttribute("user",loginUser);
        return "editpersonal";
    }

    @RequestMapping("/showallgame")
    public String showAllGame(LoginUser loginUser,Model model){
        model.addAttribute("user",loginUser);
        return "showallgame";
    }

    @RequestMapping("/showallcomment")
    public String showAllComment(LoginUser loginUser,Model model){
        model.addAttribute("user",loginUser);
        return "showallcomment";
    }
}
