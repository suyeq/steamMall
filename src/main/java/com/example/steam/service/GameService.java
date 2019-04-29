package com.example.steam.service;

import com.example.steam.dao.GameDao;
import com.example.steam.entity.Game;
import com.example.steam.entity.Image;
import com.example.steam.entity.Label;
import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.GameKey;
import com.example.steam.vo.GameDetail;
import com.example.steam.vo.SpecialGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-04-27
 * @time: 11:04
 */
@Service
public class GameService {

    @Autowired
    GameDao gameDao;
    @Autowired
    ImageService imageService;
    @Autowired
    RedisService redisService;
    @Autowired
    LabelService labelService;


    @Transactional
    public List<SpecialGame> findGamesFetured(){
        List<SpecialGame> specialGameList=redisService.get(GameKey.FETURED_GAME,GameKey.FUTURED_KEY,List.class);
        if (specialGameList!=null){
            return specialGameList;
        }
        List<Game> gameList=gameDao.findGamesFeatured();
        specialGameList=new LinkedList<>();
        for (int i=0;i<gameList.size();i++){
            Game game=gameList.get(i);
            SpecialGame specialGame=new SpecialGame();
            specialGame.setId(game.getId());
            specialGame.setDiscount(game.getDiscount());
            specialGame.setGameName(game.getGameName());
            specialGame.setGamePrice(game.getGamePrice());
            specialGame.setIssuedStatu(game.getIssuedStatu());
            specialGame.setPosterImage(imageService.findImageById(game.getPosterImage()).getUrl());
            List<Image> imageList=imageService.findGameImagesByGameId(game.getId());
            specialGame.setImageIntro1(imageList.get(0).getUrl());
            specialGame.setImageIntro2(imageList.get(1).getUrl());
            specialGame.setImageIntro3(imageList.get(2).getUrl());
            specialGame.setImageIntro4(imageList.get(3).getUrl());
            specialGameList.add(specialGame);
        }
        redisService.set(GameKey.FETURED_GAME,GameKey.FUTURED_KEY,specialGameList);
        return specialGameList;
    }

    @Transactional
    public List<SpecialGame> findSpecialGames(){
        List<SpecialGame> specialGameList=redisService.get(GameKey.SPECIAL_INDEX_GAME,GameKey.SPECIAL_INDEX_KEY,List.class);
        if (specialGameList!=null){
            return specialGameList;
        }
        List<Game> gameList=gameDao.findSpecialGames();
        specialGameList=new LinkedList<>();
        for (int i=0;i<gameList.size();i++){
            Game game=gameList.get(i);
            SpecialGame specialGame=new SpecialGame();
            specialGame.setId(game.getId());
            specialGame.setGameName(game.getGameName());
            specialGame.setPosterImage(imageService.findImageById(game.getPosterImage()).getUrl());
            specialGame.setDiscount(game.getDiscount());
            specialGame.setGamePrice(game.getGamePrice());
            specialGameList.add(specialGame);
        }
        redisService.set(GameKey.SPECIAL_INDEX_GAME,GameKey.SPECIAL_INDEX_KEY,specialGameList);
        return specialGameList;
    }

    @Transactional
    public List<GameDetail> findNewRelease(){
        List<Game> gameList=gameDao.findNewReleaseGameToIndex();
        List<GameDetail> gameDetailList=new LinkedList<>();
        for (int i=0;i<gameList.size();i++){
            Game game=gameList.get(i);
            GameDetail gameDetail=new GameDetail();
            gameDetail.setId(game.getId());
            gameDetail.setDiscount(game.getDiscount());
            gameDetail.setGameName(game.getGameName());
            gameDetail.setGamePrice(game.getGamePrice());
            gameDetail.setIssuedStatu(game.getIssuedStatu());
            gameDetail.setPosterImage(imageService.findImageById(game.getPosterImage()).getUrl());
            List<Label> labelList=labelService.findLabelsByGameId(game.getId());
            List<String> labels=new LinkedList<>();
            for (int j=0;j<labelList.size();j++){
                labels.add(labelList.get(j).getName());
            }
            gameDetail.setLabel(labels);
            gameDetailList.add(gameDetail);
        }
        return gameDetailList;
    }




}
