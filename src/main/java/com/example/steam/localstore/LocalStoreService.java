package com.example.steam.localstore;

import com.example.steam.redis.RedisService;
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

    private final Map<LocalStoreKey,String> map=new ConcurrentHashMap<>();

    public <T> void set(LocalStoreKey key,T value){
        String finalValue= RedisService.beanToString(value);
        key.setStartTime();
        map.put(key,finalValue);
    }

    public <T> T get(LocalStoreKey key,Class<T> tClass){
        long now=System.currentTimeMillis();
        if (now-key.getStartTime()>=key.getExpiredTime()){
            return null;
        }
        String value=map.get(key);
        return RedisService.stringToBean(value,tClass);
    }
}
