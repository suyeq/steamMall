package com.example.steam.service;

import com.example.steam.dao.RecentGameDao;
import com.example.steam.entity.RecentGame;
import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.GameKey;
import com.example.steam.vo.GameDetail;
import com.example.steam.vo.RecentGameVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * -----------
 * 穷则独善其身
 * 达则兼济天下
 * -----------
 *
 * @author: 苍术
 * @date: 2019-06-20
 * @time: 10:55
 */
@Service
public class RecentGameService {

    private final static int RECENT_GAME_SIZE=3;

    @Autowired
    RecentGameDao recentGameDao;
    @Autowired
    RedisService redisService;

    public List<RecentGame> findRecentGameListByEmail(String email){
        return recentGameDao.findRecentGameListByEmail(email);
    }

    /**
     * 通过email得到最近游戏情况(三个)
     * @param email
     * @return
     */
    public List<RecentGameVo> findThreeRecentGameVoListByEmail(String email){
        List<RecentGame> recentGameList=recentGameDao.findRecentGameListByEmail(email);
        List<String> gameIdList=new LinkedList<>();
        List<RecentGameVo> recentGameVoList=new LinkedList<>();
        int length=recentGameList.size()>RECENT_GAME_SIZE?RECENT_GAME_SIZE:recentGameList.size();
        for (int i=0;i<recentGameList.size();i++){
            gameIdList.add(recentGameList.get(i).getGameId()+"");
        }
        List<GameDetail> gameDetailList=redisService.getPipelineBatch(GameKey.GAME_ID,gameIdList, GameDetail.class);
        for (int i=0;i<recentGameList.size();i++){
            RecentGameVo recentGameVo=new RecentGameVo();
            recentGameVo.setId(recentGameList.get(i).getId());
            recentGameVo.setEmail(recentGameList.get(i).getEmail());
            recentGameVo.setGameId(recentGameList.get(i).getGameId());
            recentGameVo.setLastPlay(recentGameList.get(i).getLastPlay());
            recentGameVo.setPlayTime(recentGameList.get(i).getPlayTime());
            recentGameVo.setGameName(gameDetailList.get(i).getGameName());
            recentGameVo.setPosterImage(gameDetailList.get(i).getPosterImage());
            recentGameVoList.add(recentGameVo);
        }
        return recentGameVoList;
    }


}
