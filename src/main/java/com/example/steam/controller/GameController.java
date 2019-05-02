package com.example.steam.controller;

import com.alibaba.fastjson.JSON;
import com.example.steam.service.GameService;
import com.example.steam.utils.ResultMsg;
import com.example.steam.vo.SpecialGame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-04-27
 * @time: 11:02
 */
@Controller
public class GameController {

    Logger log= LoggerFactory.getLogger(GameController.class);

    @Autowired
    GameService gameService;

    @ResponseBody
    @RequestMapping("/feturedCarousel")
    public String feturedCarousel(){
        return JSON.toJSONString(ResultMsg.SUCCESS(gameService.findGamesFetured()));
    }

    @ResponseBody
    @RequestMapping("/specialCarousel")
    public String specialCarousel(){
        long start=System.currentTimeMillis();
        List<SpecialGame> list=gameService.findSpecialGames();
        long end=System.currentTimeMillis();
        long result=end-start;
        log.error(result+"");
        return JSON.toJSONString(ResultMsg.SUCCESS(list));
    }

    @ResponseBody
    @RequestMapping("/newRelease_index")
    public String newRelease(){
        return JSON.toJSONString(ResultMsg.SUCCESS(gameService.findNewRelease()));
    }

    @ResponseBody
    @RequestMapping("/hotSell_index")
    public String hotSell(){
        return JSON.toJSONString(ResultMsg.SUCCESS(gameService.findHotSell()));
    }

    @ResponseBody
    @RequestMapping("/upComing_index")
    public String upComing(){
        return JSON.toJSONString(ResultMsg.SUCCESS(gameService.findUpComing()));
    }

    @ResponseBody
    @RequestMapping("/gameDetail/{id}")
    public String OnegameDetail(@PathVariable("id")long id){
        return JSON.toJSONString(ResultMsg.SUCCESS(gameService.findGameById(id)));
    }


}
