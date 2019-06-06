package com.example.steam.entity;

import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 苍术
 * @date: 2019-06-06
 * @time: 15:51
 */
@Component
public class SpikeShopCart {

    private Long id;

    private Long userId;

    private Long spikeGameId;

    public SpikeShopCart(){}

    public SpikeShopCart(Long userId,Long spikeGameId){
        this.userId=userId;
        this.spikeGameId=spikeGameId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public Long getSpikeGameId() {
        return spikeGameId;
    }

    public void setSpikeGameId(Long spikeGameId) {
        this.spikeGameId = spikeGameId;
    }
}
