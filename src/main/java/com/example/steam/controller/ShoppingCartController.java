package com.example.steam.controller;

import com.alibaba.fastjson.JSON;
import com.example.steam.service.ShoppingCartService;
import com.example.steam.utils.ResultMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-05-19
 * @time: 13:35
 */
@Controller
public class ShoppingCartController {

    Logger logger= LoggerFactory.getLogger(ShoppingCartController.class);

    @Autowired
    ShoppingCartService shoppingCartService;

    @RequestMapping("/cart/add")
    @ResponseBody
    public String addCart(@RequestParam("userId")long userId,
                          @RequestParam("gameId")long gameId){
        logger.info(userId+" "+gameId);
        return JSON.toJSONString(ResultMsg.SUCCESS(shoppingCartService.addOneCart(userId,gameId,Integer.MAX_VALUE)));
       // return "success";
    }

    @RequestMapping("/cart/{userId}")
    @ResponseBody
    public String findCartByUserId(@PathVariable("userId")long userId){
        return JSON.toJSONString(ResultMsg.SUCCESS(shoppingCartService.findCartByUserId(userId)));
    }

    @RequestMapping("/cart/remove/{id}")
    @ResponseBody
    public String deleteGameInCartById(@PathVariable("id")long id){
        return JSON.toJSONString(ResultMsg.SUCCESS(shoppingCartService.deleteOneGameInCartById(id)));
    }

    @RequestMapping("/cart/removeall/{userId}")
    @ResponseBody
    public String deleteAllGameIncartByUserId(@PathVariable("userId")long userId){
        return JSON.toJSONString(ResultMsg.SUCCESS(shoppingCartService.deleteAllGameInCartByUserId(userId)));
    }


}
