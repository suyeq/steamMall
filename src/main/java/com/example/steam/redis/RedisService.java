package com.example.steam.redis;

import com.alibaba.fastjson.JSON;
import com.example.steam.utils.RankScoreValue;
import com.example.steam.vo.SpecialGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-04-25
 * @time: 17:20
 */
@Service
public class RedisService {

    @Autowired
    JedisPool pool;

    /**
     * 获取单个值
     * @param keyPrefix
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T get(RedisPrefixKey keyPrefix,String key,Class<T> clazz){
        Jedis jedis=null;
        try {
            jedis=pool.getResource();
            String value=jedis.get(keyPrefix.getThisPrefix()+key);
            return stringToBean(value,clazz);
        }finally {
            jedis.close();
        }
    }

    /**
     * 设置一个值
     * @param keyPrefix
     * @param key
     * @param value
     * @param <T>
     */
    public <T> void set(RedisPrefixKey keyPrefix,String key, T value){
        Jedis jedis=null;
        try{
            jedis=pool.getResource();
            String realKey=keyPrefix.getThisPrefix()+key;
            if (keyPrefix.expiredTime() <= 0){
                jedis.set(realKey,beanToString(value));
            }else {
                jedis.setex(realKey,keyPrefix.expiredTime(),beanToString(value));
            }
        }finally {
            jedis.close();
        }
    }

    /**
     * zset增加
     * @param keyPrefix
     * @param key
     * @param rank
     */
    public void zadd(RedisPrefixKey keyPrefix, String key, RankScoreValue rank){
        Jedis jedis=null;
        try {
            jedis=pool.getResource();
            String realKey=keyPrefix.getThisPrefix()+key;
            jedis.zadd(realKey,-Double.parseDouble(rank.getScore()+""),rank.getId()+"");
        }finally {
            jedis.close();
        }
    }

    /**
     * 根据起始位置与终点位置返回排名的值
     * @param keyPrefix
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> zrange(RedisPrefixKey keyPrefix,String key,long start,long end){
        Jedis jedis=null;
        try {
            jedis=pool.getResource();
            String realKey=keyPrefix.getThisPrefix()+key;
            return jedis.zrange(realKey,start,end);
        }finally {
            jedis.close();;
        }
    }


    /**
     * 判断键是否存在
     * @param keyPrefix
     * @param key
     * @return
     */
    public boolean isExists(RedisPrefixKey keyPrefix,String key){
        Jedis jedis=null;
        try {
            jedis=pool.getResource();
            String realKey=keyPrefix.getThisPrefix()+key;
            return jedis.exists(realKey);
        }finally {
            jedis.close();
        }
    }

    public static  <T> String beanToString(T value){
        if (value == null){
            //throw new NullPointerException("value  is null");
            return null;
        }
        Class clazz=value.getClass();
        if (clazz == int.class || clazz == Integer.class
                || clazz == long.class || clazz == Long.class
                || clazz == float.class || clazz == Float.class
                || clazz == double.class || clazz == Double.class){
            return value+"";
        }else  if (value.getClass() == String.class){
            return (String) value;
        }
        return JSON.toJSONString(value);
    }

    public static  <T> T stringToBean(String value,Class<T> tClass){
        if (value == null){
            //throw  new NullPointerException("value is null");
            return null;
        }
        if (tClass == String.class){
            return (T) value;
        }
        return JSON.parseObject(value,tClass);
    }

    public Long decr(RedisPrefixKey keyPrefix,String key) {
        Jedis jedis=null;
        try {
            jedis=pool.getResource();
            String realKey=keyPrefix.getThisPrefix()+key;
            return jedis.decr(realKey);
        }finally {
            jedis.close();
        }
    }


//    public static void main(String args[]){
//        List<String> list=new LinkedList<>();
//        list.add("1");
//        list.add("2");
//        list.add("3");
//        String test=beanToString(list);
//        List<String> lll=stringToBean(test,List.class);
//        System.out.println(lll.toString());
//    }
}
