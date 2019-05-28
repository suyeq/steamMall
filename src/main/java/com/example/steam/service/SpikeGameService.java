package com.example.steam.service;

import com.example.steam.dao.SpikeGameDao;
import com.example.steam.entity.SpikeGame;
import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.SpikeGameKey;
import com.example.steam.vo.SpikeGameDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public SpikeGameDetail findOneSpikeGameDetail(){
        SpikeGameDetail spikeGameDetail=redisService.get(SpikeGameKey.SPIKE_ID,1+"",SpikeGameDetail.class);
        if (spikeGameDetail!=null){
            return spikeGameDetail;
        }
        SpikeGame spikeGame=spikeGameDao.findOneById(1L);
        spikeGameDetail=new SpikeGameDetail();
        spikeGameDetail.setId(spikeGame.getId());
        spikeGameDetail.setGameId(spikeGame.getGameId());
        spikeGameDetail.setGamePrice(spikeGame.getGamePrice());
        spikeGameDetail.setSpikePrice(spikeGame.getSpikePrice());
        spikeGameDetail.setPosterImage(imageService.findImageUrlById(spikeGame.getPosterGame()));
        spikeGameDetail.setStartTime(spikeGame.getStartTime());
        spikeGameDetail.setEndTime(spikeGame.getEndTime());
        spikeGameDetail.setStockCount(spikeGame.getStockCount());
        redisService.set(SpikeGameKey.SPIKE_ID,1+"",spikeGameDetail);
        return spikeGameDetail;
    }

    public SpikeGame findOneGame(long gameId){
       return spikeGameDao.findOneByGameId(gameId);
    }
}
