package com.example.steam.controller;

import com.alibaba.fastjson.JSON;
import com.example.steam.service.LabelService;
import com.example.steam.utils.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * -----------
 * 穷则独善其身
 * 达则兼济天下
 * -----------
 *
 * @author: 苍术
 * @date: 2019-06-13
 * @time: 10:38
 */
@Controller
public class LabelController {

    @Autowired
    LabelService labelService;

    @ResponseBody
    @RequestMapping("/label/gameid")
    public String findLabelNameByGameId(@RequestParam("gameId")long gameId){
        return JSON.toJSONString(ResultMsg.SUCCESS(labelService.findLabelsByGameId(gameId)));
    }

    @ResponseBody
    @RequestMapping("/label/updatehot")
    public String updateGameLabelHotNum(@RequestParam("gameId")long gameId,
                                        @RequestParam("labelId")long labelId){
        return JSON.toJSONString(labelService.updateHotNumByGameId(gameId,labelId));
    }

    @ResponseBody
    @RequestMapping("/label/add")
    public String addLabelInGame(@RequestParam("gameId")long gameId,
                                 @RequestParam("labelName")String labelName){
        return JSON.toJSONString(labelService.addLabelInGame(gameId, labelName));
    }
}
