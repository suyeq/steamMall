package com.example.steam.entity;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 苍术
 * @date: 2019-06-02
 * @time: 21:24
 */
@Component
public class UserGame {

    private Long id;

    private String email;

    private Long gameId;

    private int playTime;

    private Date lastPlay;

    public UserGame(){}

    public UserGame(Long id,String email,Long gameId){
        this.id=id;
        this.email=email;
        this.gameId=gameId;
        this.playTime=0;
        this.lastPlay=new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public int getPlayTime() {
        return playTime;
    }

    public void setPlayTime(int playTime) {
        this.playTime = playTime;
    }

    public Date getLastPlay() {
        return lastPlay;
    }

    public void setLastPlay(Date lastPlay) {
        this.lastPlay = lastPlay;
    }
}
