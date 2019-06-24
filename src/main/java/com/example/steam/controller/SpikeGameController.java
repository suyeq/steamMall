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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger log= LoggerFactory.getLogger(SpikeGameController.class);

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
     * 处理秒杀
     * @param spikeId
     * @param path
     * @param loginUser
     * @return
     */
    @ResponseBody
    @RequestMapping("/dospike/{path}/{spikeId}")
    public String spike(@PathVariable("spikeId")long spikeId,
                        @PathVariable("path")String path,
                        LoginUser loginUser){
        log.error("path:"+path+"spikeid:"+spikeId);
        return JSON.toJSONString(spikeGameService.handleSpike(spikeId,loginUser,path));
    }

    /**
     * 隐藏秒杀接口
     * @param spikeId
     * @param loginUser
     * @return
     */
    @ResponseBody
    @RequestMapping("/spike/{spikeId}")
    public String spikePath(@PathVariable("spikeId")long spikeId,
                        LoginUser loginUser){
        return JSON.toJSONString(spikeGameService.handleRandomPathAndLimitSpike(loginUser));
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

        return JSON.toJSONString(spikeGameService.handlePollSpike(loginUser,userId,spikeId));
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        redisService.set(SpikeGameKey.SPIKE_STOCK,SpikeGameKey.SPIKE_STOCK_KEY+1,10);
    }
}
