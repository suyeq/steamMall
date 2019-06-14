package com.example.steam.mq.eventhandle;

import com.example.steam.entity.ShoppingCart;
import com.example.steam.entity.UserGame;
import com.example.steam.mq.Event;
import com.example.steam.mq.EventType;
import com.example.steam.service.ShoppingCartService;
import com.example.steam.service.SpikeShopCartService;
import com.example.steam.service.UserGameService;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.List;

/**
 * -----------
 * 穷则独善其身
 * 达则兼济天下
 * -----------
 * 买游戏处理器
 * @author: 苍术
 * @date: 2019-06-14
 * @time: 22:15
 */
public class BuyGameHandle implements EventHandle {

    @Override
    public void eventHandle(Event event, ApplicationContext applicationContext) {
        long userId=(long)event.getEtrMsg().get(Event.USER_ID);
        String email=(String) event.getEtrMsg().get(Event.EMAIL);
        List<ShoppingCart> shoppingCartList=((ShoppingCartService)applicationContext.getBean("shoppingCartService")).findShopCartByUserId(userId);
        //spikeShopCartService
        ((SpikeShopCartService)applicationContext.getBean("spikeShopCartService")).deleteSpikeShopCartByUserId(userId);
        ((ShoppingCartService)applicationContext.getBean("shoppingCartService")).deleteAllGameInCartByUserId(userId);
        for (ShoppingCart shoppingCart:shoppingCartList){
            UserGame userGame=new UserGame(0L,email,shoppingCart.getGameId());
            ((UserGameService)applicationContext.getBean("userGameService")).addGameToUser(userGame);
        }
        /**
         * 支付步骤
         */
    }

    @Override
    public List<EventType> bindEventType() {
        return Arrays.asList(EventType.BUY_GAME);
    }
}
