package com.example.steam.controller;

import com.alibaba.fastjson.JSON;
import com.example.steam.entity.SpikeShopCart;
import com.example.steam.mq.Event;
import com.example.steam.mq.EventType;
import com.example.steam.mq.MQProducer;
import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.SpikeGameKey;
import com.example.steam.service.SpikeGameService;
import com.example.steam.service.SpikeShopCartService;
import com.example.steam.utils.ResultMsg;
import com.example.steam.vo.LoginUser;
import com.example.steam.vo.SpikeGameDetail;
import org.springframework.beans.factory.InitializingBean;
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
 * @date: 2019-05-17
 * @time: 20:31
 */
@Controller
public class SpikeGameController implements InitializingBean {

    @Autowired
    MQProducer mqProducer;

    @Autowired
    RedisService redisService;

    @Autowired
    SpikeGameService spikeGameService;

    @Autowired
    SpikeShopCartService spikeShopCartService;

    @ResponseBody
    @RequestMapping("/spikegame")
    public String findOneSpikeGame(){
        return JSON.toJSONString(ResultMsg.SUCCESS(spikeGameService.findOneSpikeGameDetail(1L)));
    }

    /**
     * 第一步 预减库存
     * 第二步 查询是否已秒杀过
     * 第三步 加入消息队列（异步处理），消息处理者修改数据库
     * 第四步 不断轮询数据库，根据数据库判断秒杀是否完成
     * @param spikeId
     * @param loginUser
     * @return
     */
    @ResponseBody
    @RequestMapping("/spike/{spikeId}")
    public String spike(@PathVariable("spikeId")long spikeId,
                        LoginUser loginUser){
        if (loginUser==null){
            return JSON.toJSONString(ResultMsg.NO_LOGIN);
        }
        long stock=redisService.decKey(SpikeGameKey.SPIKE_STOCK,SpikeGameKey.SPIKE_STOCK_KEY+spikeId);
        if (stock<0){
            return JSON.toJSONString(ResultMsg.STOCK_IS_NULL);
        }
        SpikeShopCart spikeShopCart=spikeShopCartService.findSpikeShopCart(loginUser.getId(),spikeId);
        if (spikeShopCart!=null){
            return JSON.toJSONString(ResultMsg.SPIKE_REPEAT);
        }
        spikeShopCart=new SpikeShopCart(loginUser.getId(),spikeId);
        mqProducer.productEvent(new Event(EventType.SPIKE_GAME).setEtrMsg(Event.SPIKE,RedisService.beanToString(spikeShopCart)));
        return JSON.toJSONString(ResultMsg.SUCCESS(spikeShopCart));
    }

    /**
     * 轮询操作
     * @param loginUser
     * @param spikeId
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/spike/result")
    public String pollingSpikeStatu(LoginUser loginUser,
                                    @RequestParam("spikeId")long spikeId,
                                    @RequestParam("userId")long userId){
        if (loginUser==null){
            return JSON.toJSONString(ResultMsg.NO_LOGIN);
        }
        SpikeShopCart spikeShopCart=spikeShopCartService.findSpikeShopCart(userId,spikeId);
        if (spikeShopCart==null){
            return JSON.toJSONString(ResultMsg.SPIKE_ING);
        }
        return JSON.toJSONString(ResultMsg.SPIKE_SUCCESS);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        redisService.set(SpikeGameKey.SPIKE_STOCK,SpikeGameKey.SPIKE_STOCK_KEY+1,10);
    }
}
