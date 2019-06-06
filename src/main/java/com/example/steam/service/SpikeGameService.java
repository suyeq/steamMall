package com.example.steam.service;

import com.example.steam.config.DynamicDataSourceHolder;
import com.example.steam.dao.SpikeGameDao;
import com.example.steam.entity.SpikeGame;
import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.SpikeGameKey;
import com.example.steam.vo.SpikeGameDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-05-16
 * @time: 23:12
 */
@Service
public class SpikeGameService {

    @Autowired
    SpikeGameDao spikeGameDao;

    @Autowired
    ImageService imageService;

    @Autowired
    RedisService redisService;

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
}
