package com.example.steam.service;

import com.example.steam.dao.UserGameDao;
import com.example.steam.entity.UserGame;
import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.UserKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 苍术
 * @date: 2019-06-03
 * @time: 15:21
 */
@Service
public class UserGameService {

    @Autowired
    UserGameDao userGameDao;
    @Autowired
    RedisService redisService;
    @Autowired
    ApplicationContext applicationContext;

    /**
     * 找到用户下拥有的游戏id
     * @param email
     * @return
     */
    public List<Long> findGamesIdByEmail(String email){
        List<Long> containsGames=redisService.getList(UserKey.CONTAINS_GAMES,UserKey.CONTAINS_KEY+email,Long.class);
        if (containsGames!=null){
            return containsGames;
        }
        containsGames=new LinkedList<>();
        List<UserGame> userGameList=userGameDao.findGamesByEmail(email);
        for (UserGame userGame:userGameList){
            containsGames.add(userGame.getGameId());
        }
        redisService.set(UserKey.CONTAINS_GAMES,UserKey.CONTAINS_KEY+email,containsGames);
        return containsGames;
    }

    /**
     * 判断该用户下是否包含该游戏
     * @param email
     * @param gameId
     * @return
     */
    public boolean isContains(String email,long gameId){
        List<Long> gameList=((UserGameService)applicationContext.getBean("userGameService")).findGamesIdByEmail(email);
        return gameList.contains(gameId);
    }



}
