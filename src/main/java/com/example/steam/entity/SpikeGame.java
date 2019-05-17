package com.example.steam.entity;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-05-16
 * @time: 23:03
 */
@Component
public class SpikeGame {

    private Long id;

    private Long gameId;

    private Long posterGame;

    private Integer spikePrice;

    private Integer stockCount;

    private Date startTime;

    private Date endTime;

    private Integer gamePrice;

    public SpikeGame(){}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Long getPosterGame() {
        return posterGame;
    }

    public void setPosterGame(Long posterGame) {
        this.posterGame = posterGame;
    }

    public Integer getSpikePrice() {
        return spikePrice;
    }

    public void setSpikePrice(Integer spikePrice) {
        this.spikePrice = spikePrice;
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getGamePrice() {
        return gamePrice;
    }

    public void setGamePrice(Integer gamePrice) {
        this.gamePrice = gamePrice;
    }
}
