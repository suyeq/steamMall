package com.example.steam.localstore;

import com.example.steam.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * 本地存贮服务
 * @author: Suyeq
 * @date: 2019-05-03
 * @time: 21:22
 */
@Service
public class LocalStoreService {

    Logger log= LoggerFactory.getLogger(LocalStoreService.class);

    private final Map<String,String> map=new ConcurrentHashMap<>();

    public <T> void set(LocalStoreKey key,T value,String page){
        String finalValue= RedisService.beanToString(value);
        key.setExpiredTime(page);
        //key.setStartTime();
        String finalKey=key.getKeyName()+page;
        log.error(finalKey+" "+"设置本地缓存成功");
        map.put(finalKey,finalValue);
    }

    public <T> T get(LocalStoreKey key,Class<T> tClass,String page){
        long now=System.currentTimeMillis();
//        if (now-key.getStartTime()>=key.getExpiredTime()){
//            return null;
//        }
        String finalKey=key.getKeyName()+page;
        LocalStoreKey.ExpiredTime expiredTime=key.getExpiredTimeHashMap().get(page);
        if (expiredTime==null || (now-expiredTime.startTime >= expiredTime.expiredTime)){
            return null;
        }
        String value=map.get(finalKey);
        log.error(finalKey+" "+"获取本地缓存成功");
        return RedisService.stringToBean(value,tClass);
    }
}
