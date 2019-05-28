package com.example.steam.service;

import com.example.steam.config.DynamicDataSource;
import com.example.steam.config.DynamicDataSourceHolder;
import com.example.steam.dao.ShoppingCartDao;
import com.example.steam.entity.Game;
import com.example.steam.entity.ShoppingCart;
import com.example.steam.entity.SpikeGame;
import com.example.steam.vo.ShoppingCartDetail;
import org.springframework.beans.factory.annotation.Autowired;
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

    public int deleteOneGameInCartById(long id){
        return shoppingCartDao.deleteOneGameById(id);
    }

    public int deleteAllGameInCartByUserId(long userId){
        return shoppingCartDao.deleteAllGameByUserId(userId);
    }




}
