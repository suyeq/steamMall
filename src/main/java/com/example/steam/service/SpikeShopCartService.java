package com.example.steam.service;

import com.example.steam.dao.SpikeShopCartDao;
import com.example.steam.entity.SpikeShopCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 苍术
 * @date: 2019-06-06
 * @time: 15:59
 */
@Service
public class SpikeShopCartService {

    @Autowired
    SpikeShopCartDao spikeShopCartDao;

    public int addSpikeShopCart(SpikeShopCart spikeShopCart){
        return spikeShopCartDao.addSpikeShopCart(spikeShopCart);
    }

    public SpikeShopCart findSpikeShopCart(long userId,long spikeGameId){
        return spikeShopCartDao.findSpikeShopCart(userId,spikeGameId);
    }
}
