package com.example.steam.service;

import com.example.steam.dao.GameDao;
import com.example.steam.entity.*;
import com.example.steam.localstore.LocalStoreKey;
import com.example.steam.localstore.LocalStoreService;
import com.example.steam.redis.RedisPrefixKey;
import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.CommentKey;
import com.example.steam.redis.key.GameKey;
import com.example.steam.utils.GamePriorityQueue;
import com.example.steam.utils.GameRank;
import com.example.steam.utils.RankScoreValue;
import com.example.steam.utils.TimeComparator;
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

    private final static int INDEX_CAROUSEL_SIZE=12;

    private final static int CLASS_CAROUSEL_SIZE=10;

    private final static int RANK_SIZE=10;

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
    LocalStoreService localStoreService;
    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    CommentService commentService;

    /**
     * 随机10个游戏置于分类推荐
     * @param typeName
     * @return
     */
    public List<SpecialGame> findGamesToClassCarousel(String typeName){
        Set<GameDetail> gameDetailSet=localStoreService.get(LocalStoreKey.CLASS_CAROUSEL_KEY(),Set.class);
        if (gameDetailSet!=null){
            return new LinkedList<>(gameDetailSet);
        }
        int sum=redisService.get(GameKey.GAME_SUM,GameKey.GAME_SUM_KEY,int.class);
        Random random=new Random();
        gameDetailSet=new HashSet<>();
        while (gameDetailSet.size()<CLASS_CAROUSEL_SIZE){
            int seed=random.nextInt(sum)+1;
            GameDetail gameDetail=redisService.get(GameKey.GAME_ID,seed+"",GameDetail.class);
            if (gameDetail!=null && typeService.isExists(gameDetail.getType(),typeName)){
                gameDetailSet.add(gameDetail);
            }
        }
        localStoreService.set(LocalStoreKey.CLASS_CAROUSEL_KEY(),gameDetailSet);
        return new LinkedList<>(gameDetailSet);
    }

    /**
     * 找到对应类型的游戏，并按照时间降序
     * @param typeName
     * @return
     */
    public List<GameDetail> findGamesNewReleaseByType(String typeName){
        //GamePriorityQueue<GameDetail> priorityQueue=new GamePriorityQueue<>(new TimeComparator());
        List<GameDetail> gameDetailList=localStoreService.get(LocalStoreKey.NEW_RELEASE_CLASS_KEY(),List.class);
        if (gameDetailList!=null){
            return gameDetailList;
        }
        int sum=redisService.get(GameKey.GAME_SUM,GameKey.GAME_SUM_KEY,int.class);
        gameDetailList=new LinkedList<>();
        int i=0;
        while (i<sum && gameDetailList.size()<RANK_SIZE){
            Set<GameRank> rankTimeGame=redisService.zrange(GameKey.RANK_TIME,GameKey.GAME_RANK_TIME,i,i+RANK_SIZE-1,GameRank.class);
            Iterator<GameRank> iterator=rankTimeGame.iterator();
            while (iterator.hasNext()){
                GameRank gameRank=iterator.next();
                if (typeService.isExists(gameRank.getType(),typeName)&&gameDetailList.size()<RANK_SIZE){
                    GameDetail gameDetail=((GameService)applicationContext.getBean("gameService")).findGameById(gameRank.getId());
                    gameDetailList.add(gameDetail);
                }
            }
            i+=RANK_SIZE;
        }
        localStoreService.set(LocalStoreKey.NEW_RELEASE_CLASS_KEY(),gameDetailList);
        return gameDetailList;
    }

    /**
     * 找到对应类型的游戏，并按照热卖度降序
     * @param typeName
     * @return
     */
    public List<GameDetail> findGamesHotSellByType(String typeName){
        List<GameDetail> gameDetailList=localStoreService.get(LocalStoreKey.HOT_SELL_CLASS_KEY(),List.class);
        if (gameDetailList!=null){
            return gameDetailList;
        }
        int sum=redisService.get(GameKey.GAME_SUM,GameKey.GAME_SUM_KEY,int.class);
        gameDetailList=new LinkedList<>();
        int i=0;
        while (i<sum && gameDetailList.size()<RANK_SIZE){
            Set<GameRank> rankTimeGame=redisService.zrange(GameKey.RANK_SELLNUM,GameKey.GAME_RANK_SELLNUM,i,i+RANK_SIZE-1,GameRank.class);
            Iterator<GameRank> iterator=rankTimeGame.iterator();
            while (iterator.hasNext()){
                GameRank gameRank=iterator.next();
                if (typeService.isExists(gameRank.getType(),typeName) && gameDetailList.size()<RANK_SIZE){
                    GameDetail gameDetail=((GameService)applicationContext.getBean("gameService")).findGameById(gameRank.getId());
                    gameDetailList.add(gameDetail);
                }
            }
            i+=RANK_SIZE;
        }
        localStoreService.set(LocalStoreKey.HOT_SELL_CLASS_KEY(),gameDetailList);
        return gameDetailList;
    }

    /**
     * 找到对应类型的预售游戏
     * @param typeName
     * @return
     */
    public List<GameDetail> findGamesUpComingByType(String typeName){
        List<GameDetail> gameDetailList=localStoreService.get(LocalStoreKey.UP_COMING_CLASS_KEY(),List.class);
        if (gameDetailList!=null){
            return gameDetailList;
        }
        int sum=redisService.get(GameKey.GAME_SUM,GameKey.GAME_SUM_KEY,int.class);
        gameDetailList=new LinkedList<>();
        int i=0;
        while (i<sum && gameDetailList.size()<RANK_SIZE){
            Set<GameRank> rankTimeGame=redisService.zrange(GameKey.RANK_UPCOMING,GameKey.GAME_RANK_UPCOMING,i,i+RANK_SIZE-1,GameRank.class);
            Iterator<GameRank> iterator=rankTimeGame.iterator();
            while (iterator.hasNext()){
                GameRank gameRank=iterator.next();
                if (typeService.isExists(gameRank.getType(),typeName) && gameDetailList.size()<RANK_SIZE){
                    GameDetail gameDetail=((GameService)applicationContext.getBean("gameService")).findGameById(gameRank.getId());
                    gameDetailList.add(gameDetail);
                }
            }
            i+=RANK_SIZE;
        }
        localStoreService.set(LocalStoreKey.UP_COMING_CLASS_KEY(),gameDetailList);
        return gameDetailList;
    }

    /**
     *
     * 通过id查找一个游戏
     * @param id
     * @return
     */
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

    /**
     * 随机12个游戏置于首页推荐
     * @return
     */
    public List<SpecialGame> findGamesFetured(){
        Set<GameDetail> gameDetailSet=localStoreService.get(LocalStoreKey.FETURED_CAROUSEL_KEY(),Set.class);
        if (gameDetailSet != null) {
            return new LinkedList<>(gameDetailSet);
        }
        int sum=redisService.get(GameKey.GAME_SUM,GameKey.GAME_SUM_KEY,int.class);
        Random random=new Random();
        gameDetailSet=new HashSet<>();
        while (gameDetailSet.size()<INDEX_CAROUSEL_SIZE){
            int seed=random.nextInt(sum)+1;
            GameDetail gameDetail=redisService.get(GameKey.GAME_ID,seed+"",GameDetail.class);
            if (gameDetail!=null){
                gameDetailSet.add(gameDetail);
            }
        }
        localStoreService.set(LocalStoreKey.FETURED_CAROUSEL_KEY(),gameDetailSet);
        return new LinkedList<>(gameDetailSet);
    }

    /**
     * 随机12个打折游戏置于首页推荐
     * @return
     */
    public List<SpecialGame> findSpecialGames(){
        Set<GameDetail> gameDetailSet=localStoreService.get(LocalStoreKey.SPECIAL_CAROUSEL_KEY(),Set.class);
        if (gameDetailSet!=null){
            return new LinkedList<>(gameDetailSet);
        }
        int sum=redisService.get(GameKey.GAME_SUM,GameKey.GAME_SUM_KEY,int.class);
        Random random=new Random();
        gameDetailSet=new HashSet<>();
        while (gameDetailSet.size()<INDEX_CAROUSEL_SIZE){
            int seed=random.nextInt(sum)+1;
            GameDetail gameDetail=redisService.get(GameKey.GAME_ID,seed+"",GameDetail.class);
            if (gameDetail!=null && gameDetail.getDiscount()>0){
                gameDetailSet.add(gameDetail);
            }
        }
        localStoreService.set(LocalStoreKey.SPECIAL_CAROUSEL_KEY(),gameDetailSet);
        return new LinkedList<>(gameDetailSet);
    }

    /**
     * 根据页号找出最新发布的10个游戏
     * @param page
     * @return
     */
    public List<GameDetail> findNewRelease(long page){
        long cursor=0;
        List<GameDetail> gameDetailList=localStoreService.get(LocalStoreKey.NEW_RELEASE_INDEX_KEY(),List.class);
        if (gameDetailList!=null){
            return gameDetailList;
        }
        gameDetailList=new LinkedList<>();
        long sum=redisService.zcount(GameKey.RANK_TIME,GameKey.GAME_RANK_TIME);
        while (cursor<sum && gameDetailList.size()<RANK_SIZE){
            Set<GameRank> rankTimeGame=redisService.zrange(GameKey.RANK_TIME,GameKey.GAME_RANK_TIME,cursor,cursor+100, GameRank.class);
            Iterator<GameRank> iterator=rankTimeGame.iterator();
            while (iterator.hasNext()){
                GameRank gameRank=iterator.next();
                if (cursor>= page*RANK_SIZE && cursor<page*RANK_SIZE+RANK_SIZE){
                    GameDetail gameDetail=((GameService)applicationContext.getBean("gameService")).findGameById(gameRank.getId());
                    gameDetailList.add(gameDetail);
                }
                cursor++;
            }
        }
        localStoreService.set(LocalStoreKey.NEW_RELEASE_INDEX_KEY(),gameDetailList);
        return gameDetailList;
    }

    /**
     * 找出最热卖的10个游戏
     * @return
     */
    public List<GameDetail> findHotSell() {
        List<GameDetail> gameDetailList=localStoreService.get(LocalStoreKey.HOT_SELL_INDEX_KEY(),List.class);
        if (gameDetailList!=null){
            return gameDetailList;
        }
        gameDetailList=new LinkedList<>();
        Set<GameRank> rankTimeGame=redisService.zrange(GameKey.RANK_SELLNUM,GameKey.GAME_RANK_SELLNUM,0,9,GameRank.class);
        Iterator<GameRank> iterator=rankTimeGame.iterator();
        while (iterator.hasNext()){
            GameRank gameRank=iterator.next();
            GameDetail gameDetail=((GameService)applicationContext.getBean("gameService")).findGameById(gameRank.getId());
            gameDetailList.add(gameDetail);
        }
        localStoreService.set(LocalStoreKey.HOT_SELL_INDEX_KEY(),gameDetailList);
        return gameDetailList;
    }

    /**
     * 找出最近将要推出的游戏
     * @return
     */
    public List<GameDetail> findUpComing() {
        List<GameDetail> gameDetailList=localStoreService.get(LocalStoreKey.UP_COMING_INDEX_KEY(),List.class);
        if (gameDetailList!=null){
            return gameDetailList;
        }
        gameDetailList=new LinkedList<>();
        Set<GameRank> rankUpComingGame=redisService.zrange(GameKey.RANK_UPCOMING,GameKey.GAME_RANK_UPCOMING,0,9,GameRank.class);
        Iterator<GameRank> iterator=rankUpComingGame.iterator();
        while (iterator.hasNext()){
            GameRank gameRank=iterator.next();
            GameDetail gameDetail=((GameService)applicationContext.getBean("gameService")).findGameById(gameRank.getId());
            gameDetailList.add(gameDetail);
        }
        localStoreService.set(LocalStoreKey.UP_COMING_INDEX_KEY(),gameDetailList);
        return gameDetailList;
    }

    public int findGamesSum(){
        return gameDao.gamesSum();
    }

//    private List<GameDetail> indexGameToGameDetail(List<Game> gameList){
//        List<GameDetail> gameDetailList=new LinkedList<>();
//        for (int i=0;i<gameList.size();i++){
//            Game game=gameList.get(i);
//            GameDetail gameDetail=new GameDetail();
//            gameDetail.setId(game.getId());
//            gameDetail.setDiscount(game.getDiscount());
//            gameDetail.setGameName(game.getGameName());
//            gameDetail.setGamePrice(game.getGamePrice());
//            gameDetail.setIssuedStatu(game.getIssuedStatu());
//            gameDetail.setPosterImage(imageService.findImageById(game.getPosterImage()).getUrl());
//            List<Label> labelList=labelService.findLabelsByGameId(game.getId());
//            List<String> labels=new LinkedList<>();
//            for (int j=0;j<labelList.size();j++){
//                labels.add(labelList.get(j).getName());
//            }
//            gameDetail.setLabel(labels);
//            gameDetailList.add(gameDetail);
//        }
//        return gameDetailList;
//    }

    @Override
    public void afterPropertiesSet() throws Exception {
//        int sum=((GameService)applicationContext.getBean("gameService")).findGamesSum();
//        redisService.set(GameKey.GAME_SUM,GameKey.GAME_SUM_KEY,sum);
//        for (int i=0;i<sum;i++){
//
//            GameDetail gameDetail=((GameService)applicationContext.getBean("gameService")).findGameById(i+1);
//
//            RankScoreValue<GameRank> rankTime=new RankScoreValue<>();
//            RankScoreValue<GameRank> rankSellNum=new RankScoreValue<>();
//
//            GameRank gameRank=new GameRank();
//            gameRank.setId(gameDetail.getId());
//            gameRank.setType(gameDetail.getType());
//
//            rankSellNum.setScore(gameDetail.getSellNum());
//            rankSellNum.setValue(gameRank);
//
//            rankTime.setValue(gameRank);
//            rankTime.setScore(gameDetail.getIssuedDate().getTime());
//
//            redisService.set(GameKey.GAME_ID,gameDetail.getId()+"",gameDetail);
//            if (gameDetail.getIssuedStatu()!=0){
//                redisService.zadd(GameKey.RANK_TIME,GameKey.GAME_RANK_TIME,rankTime);
//                redisService.zadd(GameKey.RANK_SELLNUM,GameKey.GAME_RANK_SELLNUM,rankSellNum);
//            }
//            if (gameDetail.getIssuedStatu()!=1){
//                redisService.zadd(GameKey.RANK_UPCOMING,GameKey.GAME_RANK_UPCOMING,rankTime);
//            }
//        }
    }
}
