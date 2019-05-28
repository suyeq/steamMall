package com.example.steam.controller;

import com.alibaba.fastjson.JSON;
import com.example.steam.dao.TypeDao;
import com.example.steam.service.TypeService;
import com.example.steam.utils.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-05-18
 * @time: 12:20
 */
@Controller
public class TypeController {

    @Autowired
    TypeService typeService;

    @RequestMapping("/type/all")
    @ResponseBody
    public String findAllType(){
        return JSON.toJSONString(ResultMsg.SUCCESS(typeService.findAllType()));
    }
}
