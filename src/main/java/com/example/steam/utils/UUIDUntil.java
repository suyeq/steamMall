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
        return UUID.randomUUID().toString();
    }

//    public static void main(String []args){
//        RankScoreValue<GameRank> rankRankScoreValue=new RankScoreValue<>();
//        GameRank gameRank=new GameRank();
//        gameRank.setId(1L);
//        List<String> list=new LinkedList<>();
//        list.add("lalla");
//        list.add("kaja0");
//        gameRank.setType(list);
//        rankRankScoreValue.setScore(89);
//        rankRankScoreValue.setValue(gameRank);
//        System.out.println(RedisService.beanToString(rankRankScoreValue));
//    }
}
