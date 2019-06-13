package com.example.steam.entity;

import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-04-29
 * @time: 20:31
 */
@Component
public class GameLabel {

    private Long id;

    private Long gameId;

    private Long labelId;

    private Integer hotNum;

    public GameLabel(){}

    public GameLabel(long id,long gameId,long labelId,int hotNum){
        this.id=id;
        this.gameId=gameId;
        this.labelId=labelId;
        this.hotNum=hotNum;
    }

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

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    public Integer getHotNum() {
        return hotNum;
    }

    public void setHotNum(Integer hotNum) {
        this.hotNum = hotNum;
    }
}
