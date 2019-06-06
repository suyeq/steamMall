package com.example.steam.mq.eventhandle;

import com.example.steam.config.DynamicDataSourceHolder;
import com.example.steam.entity.SpikeGame;
import com.example.steam.entity.SpikeShopCart;
import com.example.steam.mq.Event;
import com.example.steam.mq.EventType;
import com.example.steam.redis.RedisService;
import com.example.steam.service.ShoppingCartService;
import com.example.steam.service.SpikeGameService;
import com.example.steam.service.SpikeShopCartService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 苍术
 * @date: 2019-06-06
 * @time: 10:16
 */
@Component
public class SpikeGameHandle implements EventHandle {

    /**
     * 更新库存
     * 加入秒杀购物车，用作轮询查找
     * 加入购物车
     * @param event
     * @param applicationContext
     */
    @Override
    public void eventHandle(Event event, ApplicationContext applicationContext) {
        String value=(String) event.getEtrMsg().get(Event.SPIKE);
        SpikeShopCart spikeShopCart=RedisService.stringToBean(value,SpikeShopCart.class);
        SpikeGame spikeGame=((SpikeGameService)applicationContext.getBean("spikeGameService")).findOneGameBySpikeId(spikeShopCart.getSpikeGameId(),DynamicDataSourceHolder.SLAVE);
        ((SpikeGameService)applicationContext.getBean("spikeGameService")).updateSpikeGameStockCount(spikeShopCart.getSpikeGameId());
        ((SpikeShopCartService)applicationContext.getBean("spikeShopCartService")).addSpikeShopCart(spikeShopCart);
        ((ShoppingCartService)applicationContext.getBean("shoppingCartService")).addOneCart(spikeShopCart.getUserId(),spikeGame.getGameId(),spikeGame.getSpikePrice());
    }

    @Override
    public List<EventType> bindEventType() {
        return Arrays.asList(EventType.SPIKE_GAME);
    }
}
