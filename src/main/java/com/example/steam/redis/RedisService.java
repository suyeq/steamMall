package com.example.steam.redis;

import com.alibaba.fastjson.JSON;
import com.example.steam.utils.RankScoreValue;
import com.example.steam.vo.SpecialGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.io.IOException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-04-25
 * @time: 17:20
 */
@Service
public class RedisService {

    private final static double MIN_SEED=-9999999999999d;

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
     * 转化list
     * @param keyPrefix
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> List<T> getList(RedisPrefixKey keyPrefix,String key,Class<T> clazz){
        Jedis jedis=null;
        try {
            jedis=pool.getResource();
            String value=jedis.get(keyPrefix.getThisPrefix()+key);
            return stringToArrayBean(value,clazz);
        }finally {
            jedis.close();
        }
    }

    /**
     * 通过管道技术批处理获取
     * @param keyPrefix
     * @param keyList
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> List<T> getPipelineBatch(RedisPrefixKey keyPrefix,List<String> keyList,Class<T> clazz){
        Jedis jedis=null;
        Pipeline pipeline=null;
        List<T> valueList=null;
        HashMap<String, Response<String>> intrmMap=null;
        try {
            jedis=pool.getResource();
            valueList=new LinkedList<>();
            intrmMap=new HashMap<>();
            pipeline=jedis.pipelined();
            for (String key:keyList){
                String finalKey=keyPrefix.getThisPrefix()+key;
                intrmMap.put(finalKey,pipeline.get(finalKey));
            }
            pipeline.sync();
            for (Map.Entry<String,Response<String>> entry:intrmMap.entrySet()){
                String value=entry.getValue().get();
                valueList.add(stringToBean(value,clazz));
            }
            return valueList;
        }finally {
            try {
                pipeline.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
     * 删除某个键
     * @param keyPrefix
     * @param key
     */
    public void del(RedisPrefixKey keyPrefix,String key){
        Jedis jedis=null;
        try {
            jedis=pool.getResource();
            String realKey=keyPrefix.getThisPrefix()+key;
            jedis.del(realKey);
        }finally {
            jedis.close();
        }
    }

    /**
     * 队列push
     * @param keyPrefix
     * @param key
     * @param value
     * @param <T>
     */
    public <T> void lpush(RedisPrefixKey keyPrefix,String key,T value){
        Jedis jedis=null;
        try {
            jedis=pool.getResource();
            String realKey=keyPrefix.getThisPrefix()+key;
            jedis.lpush(realKey,beanToString(value));
        }finally {
            jedis.close();
        }
    }

    /**
     * 队列pop
     * @param keyPrefix
     * @param key
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T rpop(RedisPrefixKey keyPrefix,String key,Class<T> tClass){
        Jedis jedis=null;
        try {
            jedis=pool.getResource();
            String realKey=keyPrefix.getThisPrefix()+key;
            String value=jedis.rpop(realKey);
            return stringToBean(value,tClass);
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
    public <T> void zadd(RedisPrefixKey keyPrefix, String key, RankScoreValue<T> rank){
        Jedis jedis=null;
        try {
            jedis=pool.getResource();
            String realKey=keyPrefix.getThisPrefix()+key;
            jedis.zadd(realKey,-Double.parseDouble(rank.getScore()+""),beanToString(rank.getValue()));
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
    public <T> Set<T> zrange(RedisPrefixKey keyPrefix,String key,long start,long end,Class<T> tClass){
        Jedis jedis=null;
        try {
            jedis=pool.getResource();
            String realKey=keyPrefix.getThisPrefix()+key;
            Set<String> set= jedis.zrange(realKey,start,end);
            Iterator iterator=set.iterator();
            Set<T> resultSet=new LinkedHashSet<>();
            while (iterator.hasNext()){
                resultSet.add(stringToBean((String) iterator.next(),tClass));
            }
            return resultSet;
        }finally {
            jedis.close();
        }
    }

    /**
     * 返回该键有序集合的总元素个数
     * @param keyPrefix
     * @param key
     * @return
     */
    public Long zcount(RedisPrefixKey keyPrefix,String key){
        Jedis jedis=null;
        try{
            jedis=pool.getResource();
            String realKey=keyPrefix.getThisPrefix()+key;
            return jedis.zcount(realKey,MIN_SEED,0);
        }finally {
            jedis.close();
        }
    }

    /**
     * 对zset中某一成员分数自增1
     * @param keyPrefix
     * @param key
     * @param member
     * @param <T>
     * @return
     */
    public <T> Double zincr(RedisPrefixKey keyPrefix,String key,T member){
        Jedis jedis=null;
        try{
            jedis=pool.getResource();
            String realKey=keyPrefix.getThisPrefix()+key;
            return jedis.zincrby(realKey,-1d,beanToString(member));
        }finally {
            jedis.close();
        }
    }

    /**
     * 键自增，使用于string
     * @param keyPrefix
     * @param key
     * @return
     */
    public Long decKey(RedisPrefixKey keyPrefix,String key) {
        Jedis jedis=null;
        try {
            jedis=pool.getResource();
            String realKey=keyPrefix.getThisPrefix()+key;
            return jedis.decr(realKey);
        }finally {
            jedis.close();
        }
    }

    /**
     * 键自增
     * @param keyPrefix
     * @param key
     * @return
     */
    public Long incKey(RedisPrefixKey keyPrefix,String key){
        Jedis jedis=null;
        try {
            jedis=pool.getResource();
            String realKey=keyPrefix.getThisPrefix()+key;
            return jedis.incr(realKey);
        }finally {
            jedis.close();
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

    public static <T> List<T> stringToArrayBean(String value,Class<T> tClass){
        if (value == null){
            return null;
        }
        return JSON.parseArray(value,tClass);
    }


//    public static void main(String args[]){
//
//        System.out.println(System.currentTimeMillis());
//    }
}
