package com.example.steam.service;

import com.example.steam.config.DynamicDataSource;
import com.example.steam.config.DynamicDataSourceHolder;
import com.example.steam.dao.ShoppingCartDao;
import com.example.steam.entity.*;
import com.example.steam.mq.MQProducer;
import com.example.steam.vo.ShoppingCartDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger log= LoggerFactory.getLogger(ShoppingCartService.class);

    /**
     * 检测是否已在购物车里面
     * @param email
     * @param gameId
     * @return
     */
    public boolean isContainsShopCart(String email,long gameId){
        List<ShoppingCart> shoppingCartList=((ShoppingCartService)applicationContext.getBean("shoppingCartService")).findShopCartByEmail(email);
        if (shoppingCartList!=null){
            log.error(shoppingCartList.size()+"");
        }
        for (ShoppingCart shoppingCart:shoppingCartList){
            if (shoppingCart.getGameId()==gameId){
                return true;
            }
        }
        return false;
    }

    /**
     * 找到所有的购物订单
     * @return
     */
    public List<ShoppingCartDetail> findAllCart(){
         List<ShoppingCart> shoppingCartList=shoppingCartDao.findAllCart();
         return shopCartToDetail(shoppingCartList);
    }



    /**
     * 事务会以第一个sql执行的数据源为数据源
     * 所以要在第一个语句执行前就指定好数据源
     * @param email
     * @param gameId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer addOneCart(String email,long gameId,int gamePrice){
        ShoppingCart shoppingCart=new ShoppingCart();
        Game game=gameService.findOneGameById(gameId,DynamicDataSourceHolder.MASTER);
        shoppingCart.setGameId(gameId);
        shoppingCart.setGameName(game.getGameName());
        int finalPrice=game.getDiscount()==0?game.getGamePrice():(int)Math.ceil((double)game.getDiscount()/100*game.getGamePrice());
        finalPrice=finalPrice>gamePrice?gamePrice:finalPrice;
        shoppingCart.setGamePrice(finalPrice);
        shoppingCart.setPosterImage(game.getPosterImage());
        shoppingCart.setEmail(email);
        return shoppingCartDao.addOneCart(shoppingCart);
    }

    /**
     * 通过用户邮箱得到购物车
     * @param email
     * @return
     */
    public List<ShoppingCartDetail> findCartByUserEmail(String email){
        List<ShoppingCart> cartList=shoppingCartDao.findCartByUserEmail(email);
        return shopCartToDetail(cartList);
    }

//    /**
//     * 最终购买游戏
//     * @param userId
//     * @return
//     */
//    public int addBuyGame(long userId,String email){
//        List<ShoppingCart> shoppingCartList=shoppingCartDao.findCartByUserId(userId);
//        int result=spikeShopCartService.deleteSpikeShopCartByUserId(userId);
//        ((ShoppingCartService)applicationContext.getBean("shoppingCartService")).deleteAllGameInCartByUserId(userId);
//        for (ShoppingCart shoppingCart:shoppingCartList){
//            UserGame userGame=new UserGame(0L,email,shoppingCart.getGameId());
//            userGameService.addGameToUser(userGame);
//        }
//        /**
//         * 支付
//         */
//        return result;
//    }

    /**
     * 通过邮箱找到该用户下的购物车简略
     * @param email
     * @return
     */
    public List<ShoppingCart> findShopCartByEmail(String email){
        return shoppingCartDao.findCartByUserEmail(email);
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
     * 通过邮箱全部删除
     * @param email
     * @return
     */
    public int deleteAllGameInCartByUserEmail(String email){
        return shoppingCartDao.deleteAllGameByUserEmail(email);
    }


    private List<ShoppingCartDetail> shopCartToDetail(List<ShoppingCart> shoppingCartList){
        List<ShoppingCartDetail> shoppingCartDetailList=new LinkedList<>();
        for (int i=0;i<shoppingCartList.size();i++){
            ShoppingCartDetail shoppingCartDetail=new ShoppingCartDetail();
            ShoppingCart shoppingCart=shoppingCartList.get(i);
            shoppingCartDetail.setId(shoppingCart.getId());
            shoppingCartDetail.setEmail(shoppingCart.getEmail());
            shoppingCartDetail.setGamePoster(imageService.findImageUrlById(shoppingCart.getPosterImage()));
            shoppingCartDetail.setGameId(shoppingCart.getGameId());
            shoppingCartDetail.setGameName(shoppingCart.getGameName());
            shoppingCartDetail.setGamePrice(shoppingCart.getGamePrice());
            shoppingCartDetailList.add(shoppingCartDetail);
        }
        return shoppingCartDetailList;
    }

}
