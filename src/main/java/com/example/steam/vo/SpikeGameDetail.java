package com.example.steam.vo;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-05-17
 * @time: 20:22
 */
public class SpikeGameDetail {

    private Long id;

    private Long gameId;

    private Integer spikePrice;

    private Integer stockCount;

    private Date startTime;

    private Date endTime;

    private Integer gamePrice;

    private String posterImage;

    public SpikeGameDetail(){}


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

    public String getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(String posterImage) {
        this.posterImage = posterImage;
    }
}
