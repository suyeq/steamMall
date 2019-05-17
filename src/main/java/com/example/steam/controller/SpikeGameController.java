package com.example.steam.controller;

import com.alibaba.fastjson.JSON;
import com.example.steam.service.SpikeGameService;
import com.example.steam.utils.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-05-17
 * @time: 20:31
 */
@Controller
public class SpikeGameController {

    @Autowired
    SpikeGameService spikeGameService;

    @RequestMapping("/spikegame")
    @ResponseBody
    public String findOneSpikeGame(){
        return JSON.toJSONString(ResultMsg.SUCCESS(spikeGameService.findOneSpikeGame()));
    }
}
