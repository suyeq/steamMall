package com.example.steam.entity;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 *
 * @author: 苍术
 * @date: 2019-06-20
 * @time: 10:45
 */
@Component
public class RecentGame {

    private Long id;

    private String email;

    private Long gameId;

    private Date lastPlay;

    private Integer playTime;

    public RecentGame(){}

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

    public Date getLastPlay() {
        return lastPlay;
    }

    public void setLastPlay(Date lastPlay) {
        this.lastPlay = lastPlay;
    }

    public Integer getPlayTime() {
        return playTime;
    }

    public void setPlayTime(Integer playTime) {
        this.playTime = playTime;
    }
}
