package com.example.steam.controller;

import com.alibaba.fastjson.JSON;
import com.example.steam.service.SensitiveWordService;
import com.example.steam.utils.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: 苍术
 * @date: 2019-07-04
 * @time: 20:43
 */
@Controller
public class SensitiveController {

    @Autowired
    SensitiveWordService sensitiveWordService;

    @ResponseBody
    @RequestMapping("/sensitive/save")
    public String saveSensitive(@RequestParam("sensitive")String sensitive){
        String [] words=sensitive.split("\\|");
        return JSON.toJSONString(ResultMsg.SUCCESS(sensitiveWordService.handleSaveWord(words)));
    }
}
