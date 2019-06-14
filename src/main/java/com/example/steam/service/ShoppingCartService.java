package com.example.steam.service;

import com.example.steam.config.DynamicDataSource;
import com.example.steam.config.DynamicDataSourceHolder;
import com.example.steam.dao.ShoppingCartDao;
import com.example.steam.entity.*;
import com.example.steam.mq.MQProducer;
import com.example.steam.vo.ShoppingCartDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-05-19
 * @time: 12:00
 */
@Service
public class ShoppingCartService {

    @Autowired
    ShoppingCartDao shoppingCartDao;
    @Autowired
    GameService gameService;
    @Autowired
    ImageService imageService;
    @Autowired
    SpikeGameService spikeGameService;
    @Autowired
    SpikeShopCartService spikeShopCartService;
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    UserGameService userGameService;
    /**
     * 事务会以第一个sql执行的数据源为数据源
     * 所以要在第一个语句执行前就指定好数据源
     * @param userId
     * @param gameId
     * @return
     */
    @Transactional
    public Integer addOneCart(long userId,long gameId,int gamePrice){
        ShoppingCart shoppingCart=new ShoppingCart();
        Game game=gameService.findOneGameById(gameId,DynamicDataSourceHolder.MASTER);
        shoppingCart.setGameId(gameId);
        shoppingCart.setGameName(game.getGameName());
        int finalPrice=game.getDiscount()==0?game.getGamePrice():(int)Math.ceil((double)game.getDiscount()/100*game.getGamePrice());
        finalPrice=finalPrice>gamePrice?gamePrice:finalPrice;
        shoppingCart.setGamePrice(finalPrice);
        shoppingCart.setPosterImage(game.getPosterImage());
        shoppingCart.setUserId(userId);
        return shoppingCartDao.addOneCart(shoppingCart);
    }

    /**
     * 通过用户id得到购物车
     * @param userId
     * @return
     */
    public List<ShoppingCartDetail> findCartByUserId(long userId){
        List<ShoppingCart> cartList=shoppingCartDao.findCartByUserId(userId);
        List<ShoppingCartDetail> shoppingCartDetailList=new LinkedList<>();
        for (int i=0;i<cartList.size();i++){
            ShoppingCartDetail shoppingCartDetail=new ShoppingCartDetail();
            ShoppingCart shoppingCart=cartList.get(i);
            shoppingCartDetail.setId(shoppingCart.getId());
            shoppingCartDetail.setUserId(shoppingCart.getUserId());
            shoppingCartDetail.setGamePoster(imageService.findImageUrlById(shoppingCart.getPosterImage()));
            shoppingCartDetail.setGameId(shoppingCart.getGameId());
            shoppingCartDetail.setGameName(shoppingCart.getGameName());
            shoppingCartDetail.setGamePrice(shoppingCart.getGamePrice());
            shoppingCartDetailList.add(shoppingCartDetail);
        }
        return shoppingCartDetailList;
    }

    /**
     * 最终购买游戏
     * @param userId
     * @return
     */
    public int addBuyGame(long userId,String email){
        List<ShoppingCart> shoppingCartList=shoppingCartDao.findCartByUserId(userId);
        int result=spikeShopCartService.deleteSpikeShopCartByUserId(userId);
        ((ShoppingCartService)applicationContext.getBean("shoppingCartService")).deleteAllGameInCartByUserId(userId);
        for (ShoppingCart shoppingCart:shoppingCartList){
            UserGame userGame=new UserGame(0L,email,shoppingCart.getGameId());
            userGameService.addGameToUser(userGame);
        }
        /**
         * 支付
         */
        return result;
    }

    /**
     * 通过userid找到该用户下的购物车简略
     * @param userId
     * @return
     */
    public List<ShoppingCart> findShopCartByUserId(long userId){
        return shoppingCartDao.findCartByUserId(userId);
    }

    /**
     * 通过id删除
     * @param id
     * @return
     */
    public int deleteOneGameInCartById(long id){
        return shoppingCartDao.deleteOneGameById(id);
    }

    /**
     * 通过用户id全部删除
     * @param userId
     * @return
     */
    public int deleteAllGameInCartByUserId(long userId){
        return shoppingCartDao.deleteAllGameByUserId(userId);
    }




}
