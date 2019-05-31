package com.example.steam.mq;

import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.MQKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * 消息生产者
 * @author: Suyeq
 * @date: 2019-05-29
 * @time: 20:34
 */
@Service
public class MQProducer{

    @Autowired
    RedisService redisService;

    public void productEvent(Event event){
        redisService.lpush(MQKey.MQ, Event.EVENT_KEY,event);
    }
}
