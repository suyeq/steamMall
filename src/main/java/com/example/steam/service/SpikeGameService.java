package com.example.steam.service;

import com.alibaba.fastjson.JSON;
import com.example.steam.config.DynamicDataSourceHolder;
import com.example.steam.dao.SpikeGameDao;
import com.example.steam.entity.SpikeGame;
import com.example.steam.entity.SpikeShopCart;
import com.example.steam.mq.Event;
import com.example.steam.mq.EventType;
import com.example.steam.mq.MQProducer;
import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.GameKey;
import com.example.steam.redis.key.SpikeGameKey;
import com.example.steam.redis.key.UserKey;
import com.example.steam.utils.ResultMsg;
import com.example.steam.utils.UUIDUtil;
import com.example.steam.vo.GameDetail;
import com.example.steam.vo.LoginUser;
import com.example.steam.vo.SpikeGameDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-05-16
 * @time: 23:12
 */
@Service
public class SpikeGameService {

    //最大每分钟秒杀次数
    private final static int MAX_SPIKETIMES_EVERY_MINUTE=10;

    Logger log= LoggerFactory.getLogger(SpikeGameService.class);

    @Autowired
    SpikeGameDao spikeGameDao;

    @Autowired
    ImageService imageService;

    @Autowired
    RedisService redisService;

    @Autowired
    MQProducer mqProducer;

    @Autowired
    SpikeGameService spikeGameService;

    @Autowired
    SpikeShopCartService spikeShopCartService;

    @Autowired
    ApplicationContext applicationContext;

    /**
     * 通过id找到一个详细的秒杀游戏信息
     * @param spikeId
     * @return
     */
    public SpikeGameDetail findOneSpikeGameDetail(long spikeId){
        SpikeGameDetail spikeGameDetail=redisService.get(SpikeGameKey.SPIKE_ID,spikeId+"",SpikeGameDetail.class);
        if (spikeGameDetail!=null){
            return spikeGameDetail;
        }
        SpikeGame spikeGame=spikeGameDao.findOneById(spikeId);
        spikeGameDetail=new SpikeGameDetail();
        spikeGameDetail.setId(spikeGame.getId());
        spikeGameDetail.setGameId(spikeGame.getGameId());
        spikeGameDetail.setGamePrice(spikeGame.getGamePrice());
        spikeGameDetail.setSpikePrice(spikeGame.getSpikePrice());
        spikeGameDetail.setPosterImage(imageService.findImageUrlById(spikeGame.getPosterGame()));
        spikeGameDetail.setStartTime(spikeGame.getStartTime());
        spikeGameDetail.setEndTime(spikeGame.getEndTime());
        spikeGameDetail.setStockCount(spikeGame.getStockCount());
        redisService.set(SpikeGameKey.SPIKE_ID,spikeId+"",spikeGameDetail);
        return spikeGameDetail;
    }

    /**
     * 先更新缓存，再更新主库
     * 防止数据不一致
     * @param spikeGame
     * @return
     */
    public int updateOneSpikeGame(SpikeGame spikeGame){
        SpikeGameDetail spikeGameDetail=new SpikeGameDetail();
        spikeGameDetail.setId(spikeGame.getId());
        spikeGameDetail.setGameId(spikeGame.getGameId());
        spikeGameDetail.setGamePrice(spikeGame.getGamePrice());
        spikeGameDetail.setSpikePrice(spikeGame.getSpikePrice());
        spikeGameDetail.setPosterImage(imageService.findImageUrlById(spikeGame.getPosterGame(),DynamicDataSourceHolder.MASTER));
        spikeGameDetail.setStartTime(spikeGame.getStartTime());
        spikeGameDetail.setEndTime(spikeGame.getEndTime());
        spikeGameDetail.setStockCount(spikeGame.getStockCount());
        redisService.set(SpikeGameKey.SPIKE_ID,spikeGame.getId()+"",spikeGameDetail);
        return spikeGameDao.updateOneSpikeGame(spikeGame);
    }

    /**
     * 更新秒杀游戏的库存
     * @param spikeId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int updateSpikeGameStockCount(long spikeId){
        SpikeGame spikeGame=((SpikeGameService)applicationContext.getBean("spikeGameService")).findOneGameBySpikeId(spikeId,DynamicDataSourceHolder.MASTER);
        spikeGame.setStockCount(spikeGame.getStockCount()-1);
        return ((SpikeGameService)applicationContext.getBean("spikeGameService")).updateOneSpikeGame(spikeGame);
    }

    public SpikeGame findOneGameByGameId(long gameId){
       return spikeGameDao.findOneByGameId(gameId);
    }

    public SpikeGame findOneGameBySpikeId(long spikeId,String dataSource){
        DynamicDataSourceHolder.putDataSource(dataSource);
        return spikeGameDao.findOneById(spikeId);
    }

    /**
     * 处理秒杀
     *  * 第一步 预减库存
     *  * 第二步 查询是否已秒杀过
     *  * 第三步 加入消息队列（异步处理），消息处理者修改数据库
     *  * 第四步 不断轮询数据库，根据数据库判断秒杀是否完成
     * @param spikeId
     * @param loginUser
     * @return
     */
    public ResultMsg handleSpike(long spikeId, LoginUser loginUser,String path) {
        if (loginUser==null){
            return ResultMsg.NO_LOGIN;
        }
        String spikePath=redisService.get(SpikeGameKey.RANDM_PATH,SpikeGameKey.RANDM_PATH_KEY+loginUser.getEmail(),String.class);
        if (!spikePath.equals(path)){
            return ResultMsg.SPIKE_PATH_ERROR;
        }
        long stock=redisService.decKey(SpikeGameKey.SPIKE_STOCK,SpikeGameKey.SPIKE_STOCK_KEY+spikeId);
        if (stock<0){
            return ResultMsg.STOCK_IS_NULL;
        }
        List<Long> containsGames=redisService.getList(UserKey.CONTAINS_GAMES,UserKey.CONTAINS_KEY+loginUser.getEmail(),Long.class);
        SpikeGameDetail spikeGameDetail=spikeGameService.findOneSpikeGameDetail(spikeId);
        if (containsGames.contains(spikeGameDetail.getGameId())){
            return ResultMsg.GAME_HAD;
        }
        SpikeShopCart spikeShopCart=spikeShopCartService.findSpikeShopCart(loginUser.getEmail(),spikeId);
        if (spikeShopCart!=null){
            return ResultMsg.SPIKE_REPEAT;
        }
        spikeShopCart=new SpikeShopCart(loginUser.getEmail(),spikeId);
        mqProducer.productEvent(new Event(EventType.SPIKE_GAME).setEtrMsg(Event.SPIKE,RedisService.beanToString(spikeShopCart)));
        return ResultMsg.SUCCESS(spikeShopCart);
    }

    /**
     * 处理轮询操作
     * @param loginUser
     * @param userId
     * @param spikeId
     * @return
     */
    public ResultMsg handlePollSpike(LoginUser loginUser, long userId, long spikeId) {
        if (loginUser==null){
            return ResultMsg.NO_LOGIN;
        }
        SpikeShopCart spikeShopCart=spikeShopCartService.findSpikeShopCart(loginUser.getEmail(),spikeId);
        if (spikeShopCart==null){
            return ResultMsg.SPIKE_ING;
        }
        return ResultMsg.SPIKE_SUCCESS;
    }

    /**
     * 随机秒杀路径，且限流
     * @param loginUser
     * @return
     */
    public ResultMsg handleRandomPathAndLimitSpike(LoginUser loginUser) {
        if (loginUser==null){
            return ResultMsg.NO_LOGIN;
        }
        Integer spikeTimes=redisService.get(SpikeGameKey.SPIKE_TIMES,SpikeGameKey.SPIKE_TIMES_KEY+loginUser.getEmail(),Integer.class);
        spikeTimes=spikeTimes==null?new Integer(0):spikeTimes;
        if (spikeTimes>MAX_SPIKETIMES_EVERY_MINUTE){
            return ResultMsg.SPIKE_LIMIT_ERROR;
        }
        String uuId= UUIDUtil.randomUUID();
        spikeTimes++;
        redisService.set(SpikeGameKey.SPIKE_TIMES,SpikeGameKey.SPIKE_TIMES_KEY+loginUser.getEmail(),spikeTimes);
        redisService.set(SpikeGameKey.RANDM_PATH,SpikeGameKey.RANDM_PATH_KEY+loginUser.getEmail(),uuId);
        return ResultMsg.SUCCESS(uuId);
    }
}
