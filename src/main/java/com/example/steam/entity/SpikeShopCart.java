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

    private String email;

    private Long spikeGameId;

    public SpikeShopCart(){}

    public SpikeShopCart(String email,Long spikeGameId){
        this.email=email;
        this.spikeGameId=spikeGameId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSpikeGameId() {
        return spikeGameId;
    }

    public void setSpikeGameId(Long spikeGameId) {
        this.spikeGameId = spikeGameId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
