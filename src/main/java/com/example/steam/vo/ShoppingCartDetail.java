package com.example.steam.vo;

import com.example.steam.entity.ShoppingCart;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-05-19
 * @time: 11:32
 */
public class ShoppingCartDetail extends ShoppingCart {

    private String gamePoster;

    public ShoppingCartDetail(){}

    public String getGamePoster() {
        return gamePoster;
    }

    public void setGamePoster(String gamePoster) {
        this.gamePoster = gamePoster;
    }
}
