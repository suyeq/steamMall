package com.example.steam.service;

import com.example.steam.dao.UserGameDao;
import com.example.steam.entity.RecentGame;
import com.example.steam.entity.UserGame;
import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.GameKey;
import com.example.steam.redis.key.UserKey;
import com.example.steam.vo.GameDetail;
import com.example.steam.vo.RecentGameVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 苍术
 * @date: 2019-06-03
 * @time: 15:21
 */
@Service
public class UserGameService {

    private final static int RECENT_GAME_SIZE=3;

    @Autowired
    UserGameDao userGameDao;
    @Autowired
    RedisService redisService;
    @Autowired
    ApplicationContext applicationContext;

    /**
     * 通过email得到最近游戏情况(三个)
     * @param email
     * @return
     */
    public List<RecentGameVo> findThreeRecentGameVoListByEmail(String email){
        List<UserGame> userGameList=userGameDao.findGamesByEmailOrderByLastPlayDesc(email);
        List<String> gameIdList=new LinkedList<>();
        List<RecentGameVo> recentGameVoList=new LinkedList<>();
        Map<Long,GameDetail> map=new HashMap<>();
        int length=userGameList.size()>RECENT_GAME_SIZE?RECENT_GAME_SIZE:userGameList.size();
        for (int i=0;i<userGameList.size();i++){
            gameIdList.add(userGameList.get(i).getGameId()+"");
        }
        List<GameDetail> gameDetailList=redisService.getPipelineBatch(GameKey.GAME_ID,gameIdList, GameDetail.class);
        for (GameDetail gameDetail:gameDetailList){
            map.put(gameDetail.getId(),gameDetail);
        }
        for (int i=0;i<length;i++){
            RecentGameVo recentGameVo=new RecentGameVo();
            recentGameVo.setId(userGameList.get(i).getId());
            recentGameVo.setEmail(userGameList.get(i).getEmail());
            recentGameVo.setGameId(userGameList.get(i).getGameId());
            recentGameVo.setLastPlay(userGameList.get(i).getLastPlay());
            recentGameVo.setPlayTime(userGameList.get(i).getPlayTime());
            recentGameVo.setGameName(map.get(userGameList.get(i).getGameId()).getGameName());
            recentGameVo.setPosterImage(map.get(userGameList.get(i).getGameId()).getPosterImage());
            recentGameVoList.add(recentGameVo);
        }
        return recentGameVoList;
    }


    public UserGame findOneUserGameByEmailAndGameId(String email,long gameId){
        return userGameDao.findGameByEmailAndGameId(email,gameId);
    }

    /**
     * 直接通过email找出用户与游戏之间的关系
     * @param email
     * @return
     */
    public List<UserGame> findUserGameListByEmail(String email){
        return userGameDao.findGamesByEmail(email);
    }

    /**
     * 该用户下增加一个游戏
     * @param userGame
     * @return
     */
    public int addGameToUser(UserGame userGame){
        List<Long> containsGames=redisService.getList(UserKey.CONTAINS_GAMES,UserKey.CONTAINS_KEY+userGame.getEmail(),Long.class);
        containsGames.add(userGame.getGameId());
        redisService.set(UserKey.CONTAINS_GAMES,UserKey.CONTAINS_KEY+userGame.getEmail(),containsGames);
        return userGameDao.addGameToUser(userGame);
    }

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
