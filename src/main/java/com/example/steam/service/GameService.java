package com.example.steam.service;

import com.example.steam.dao.GameDao;
import com.example.steam.entity.Game;
import com.example.steam.entity.GameImage;
import com.example.steam.entity.Image;
import com.example.steam.entity.Label;
import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.GameKey;
import com.example.steam.vo.GameDetail;
import com.example.steam.vo.SpecialGame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-04-27
 * @time: 11:04
 */
@Service
public class GameService implements InitializingBean {

    Logger log= LoggerFactory.getLogger(GameService.class);

    @Autowired
    GameDao gameDao;
    @Autowired
    ImageService imageService;
    @Autowired
    RedisService redisService;
    @Autowired
    LabelService labelService;
    @Autowired
    TypeService typeService;
    @Autowired
    ApplicationContext applicationContext;

    @Transactional
    public List<SpecialGame> findGamesToClassCarousel(String typeName){
        int sum=redisService.get(GameKey.GAME_SUM,GameKey.GAME_SUM_KEY,int.class);
        log.error(sum+"");
        Random random=new Random();
        Set<GameDetail> gameDetailSet=new HashSet<>();
        while (gameDetailSet.size()<10){
            int seed=random.nextInt(sum)+1;
            GameDetail gameDetail=redisService.get(GameKey.GAME_ID,seed+"",GameDetail.class);
            if (gameDetail!=null && typeService.isExists(gameDetail.getType(),typeName)){
                gameDetailSet.add(gameDetail);
            }
        }
        log.error(gameDetailSet.toString());
        return new LinkedList<>(gameDetailSet);
    }

    @Transactional
    public GameDetail findGameById(long id){
        GameDetail gameDetail=redisService.get(GameKey.GAME_ID,id+"",GameDetail.class);
        if (gameDetail!=null){
            return gameDetail;
        }
        Game game=gameDao.findGameById(id);
        gameDetail=new GameDetail();
        gameDetail.setId(game.getId());
        gameDetail.setGameName(game.getGameName());
        gameDetail.setGamePrice(game.getGamePrice());
        gameDetail.setDiscount(game.getDiscount());
        gameDetail.setGameIntroduction(game.getGameIntroduction());
        gameDetail.setGameAbout(game.getGameAbout());
        gameDetail.setIssuedDate(game.getIssuedDate());
        gameDetail.setSellNum(game.getSellNum());
        gameDetail.setIssuedStatu(game.getIssuedStatu());
        gameDetail.setPosterImage(imageService.findImageUrlById(game.getPosterImage()));
        List<String> imageUrlList=imageService.findGameImageUrlsByGameId(game.getId());
        gameDetail.setImageIntro1(imageUrlList.get(0));
        gameDetail.setImageIntro2(imageUrlList.get(1));
        gameDetail.setImageIntro3(imageUrlList.get(2));
        gameDetail.setImageIntro4(imageUrlList.get(3));
        gameDetail.setImageIntro5(imageUrlList.get(4));
        gameDetail.setLabel(labelService.findLabelNamesByGameId(game.getId()));
        gameDetail.setLowestSystem(game.getLowestSystem());
        gameDetail.setRecommendSystem(game.getRecommendSystem());
        gameDetail.setType(typeService.findTypeNameByGameId(game.getId()));
        redisService.set(GameKey.GAME_ID,id+"",gameDetail);
        return gameDetail;
    }

    @Transactional
    public List<SpecialGame> findGamesFetured(){
        long start=System.currentTimeMillis();
        List<SpecialGame> specialGameList=redisService.get(GameKey.FETURED_GAME,GameKey.FUTURED_KEY,List.class);
        long end=System.currentTimeMillis();
        long result=end-start;
        log.error(result+"redis");
        if (specialGameList!=null){
            return specialGameList;
        }
        start=System.currentTimeMillis();
        List<Game> gameList=gameDao.findGamesFeatured();
        end=System.currentTimeMillis();
        log.error(end-start+"查询数据库了");
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
        List<GameDetail> gameDetailList=redisService.get(GameKey.NEW_RELEASE_INDEX_GAME,GameKey.NEW_RELEASE_INDEX_KEY,List.class);
        if (gameDetailList!=null){
            return gameDetailList;
        }
        List<Game> gameList=gameDao.findNewReleaseGameToIndex();
        gameDetailList=indexGameToGameDetail(gameList);
        redisService.set(GameKey.NEW_RELEASE_INDEX_GAME,GameKey.NEW_RELEASE_INDEX_KEY,gameDetailList);
        return gameDetailList;
    }

    @Transactional
    public List<GameDetail> findHotSell() {
        List<GameDetail> gameDetailList=redisService.get(GameKey.HOT_SELL_INDEX_GAME,GameKey.HOT_SELL_INDEX_KEY,List.class);
        if (gameDetailList!=null){
            return gameDetailList;
        }
        List<Game> gameList=gameDao.findHotSellGameToIndex();
        gameDetailList=indexGameToGameDetail(gameList);
        redisService.set(GameKey.HOT_SELL_INDEX_GAME,GameKey.HOT_SELL_INDEX_KEY,gameDetailList);
        return gameDetailList;
    }

    @Transactional
    public List<GameDetail> findUpComing() {
        List<GameDetail> gameDetailList=redisService.get(GameKey.UP_COMING_INDEX_GAME,GameKey.UP_COMING_INDEX_KEY,List.class);
        if (gameDetailList!=null){
            return gameDetailList;
        }
        List<Game> gameList=gameDao.findUpComingGameToIndex();
        gameDetailList=indexGameToGameDetail(gameList);
        redisService.set(GameKey.UP_COMING_INDEX_GAME,GameKey.UP_COMING_INDEX_KEY,gameDetailList);
        return gameDetailList;
    }

    public int findGamesSum(){
        return gameDao.gamesSum();
    }

    private List<GameDetail> indexGameToGameDetail(List<Game> gameList){
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

    @Override
    public void afterPropertiesSet() throws Exception {
        int sum=((GameService)applicationContext.getBean("gameService")).findGamesSum();
        redisService.set(GameKey.GAME_SUM,GameKey.GAME_SUM_KEY,sum);
    }
}
