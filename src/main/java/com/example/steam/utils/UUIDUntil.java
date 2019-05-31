package com.example.steam.utils;

import com.example.steam.redis.RedisService;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-04-26
 * @time: 8:15
 */
public class UUIDUntil {

    public static String randomUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

//    public static void main(String []args){
//        System.out.println(randomUUID());
//    }
}
