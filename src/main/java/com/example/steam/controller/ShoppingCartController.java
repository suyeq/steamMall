package com.example.steam.controller;

import com.alibaba.fastjson.JSON;
import com.example.steam.mq.Event;
import com.example.steam.mq.EventType;
import com.example.steam.mq.MQProducer;
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
    @Autowired
    MQProducer mqProducer;

    @RequestMapping("/cart/add")
    @ResponseBody
    public String addCart(@RequestParam("email")String email,
                          @RequestParam("gameId")long gameId){
        logger.info(email+" "+gameId);
        return JSON.toJSONString(ResultMsg.SUCCESS(shoppingCartService.addOneCart(email,gameId,Integer.MAX_VALUE)));
       // return "success";
    }

    @RequestMapping("/cart/{email}")
    @ResponseBody
    public String findCartByUserId(@PathVariable("email")String email){
        return JSON.toJSONString(ResultMsg.SUCCESS(shoppingCartService.findCartByUserEmail(email)));
    }

    @RequestMapping("/cart/remove/{id}")
    @ResponseBody
    public String deleteGameInCartById(@PathVariable("id")long id){
        return JSON.toJSONString(ResultMsg.SUCCESS(shoppingCartService.deleteOneGameInCartById(id)));
    }

    @RequestMapping("/cart/removeall/{email}")
    @ResponseBody
    public String deleteAllGameIncartByUserId(@PathVariable("email")String email){
        return JSON.toJSONString(ResultMsg.SUCCESS(shoppingCartService.deleteAllGameInCartByUserEmail(email)));
    }

    @ResponseBody
    @RequestMapping("/buygame")
    public String finalBuyGame(@RequestParam("userId")long userId,
                               @RequestParam("email")String email){
        mqProducer.productEvent(new Event(EventType.BUY_GAME).setEtrMsg(Event.EMAIL,email).setEtrMsg(Event.USER_ID,userId+""));
        return JSON.toJSONString(ResultMsg.SUCCESS);
    }

    @ResponseBody
    @RequestMapping("/cart/iscontain")
    public String isContainsCart(@RequestParam("email")String email,
                                 @RequestParam("gameId")long gameId){
        return JSON.toJSONString(ResultMsg.SUCCESS(shoppingCartService.isContainsShopCart(email,gameId)));
    }


}
