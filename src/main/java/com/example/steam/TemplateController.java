package com.example.steam;

import com.example.steam.entity.Type;
import com.example.steam.service.GameService;
import com.example.steam.service.ImageService;
import com.example.steam.service.TypeService;
import com.example.steam.vo.SpecialGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
    @Autowired
    GameService gameService;
    @Autowired
    ImageService imageService;


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
        long start=System.currentTimeMillis();
        imageService.findImageById(1);
        long end=System.currentTimeMillis();
        long result=end-start;
        System.out.println(result);
        return "ok";
    }



}
