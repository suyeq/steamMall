package com.example.steam.controller;

import com.alibaba.fastjson.JSON;
import com.example.steam.service.SystemNeedService;
import com.example.steam.utils.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-05-06
 * @time: 22:42
 */
@Controller
public class SystemNeedController {

    @Autowired
    SystemNeedService systemNeedService;

    @ResponseBody
    @RequestMapping("/systemneed/{id}")
    public String findSystemNeedById(@PathVariable("id")long id){
        return JSON.toJSONString(ResultMsg.SUCCESS(systemNeedService.findSystemNeedById(id)));
    }
}
