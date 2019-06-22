package com.example.steam.vo;

/**
 * @author: 苍术
 * @date: 2019-06-21
 * @time: 20:26
 */
public class SimpleGameShowVo {

    private long gameId;

    private String gameName;

    private String posterImage;

    private int playTime;

    public SimpleGameShowVo(){}

    public SimpleGameShowVo(long gameId,String gameName,String posterImage,int playTime){
        this.gameId=gameId;
        this.gameName=gameName;
        this.posterImage=posterImage;
        this.playTime=playTime;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public String getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(String posterImage) {
        this.posterImage = posterImage;
    }

    public int getPlayTime() {
        return playTime;
    }

    public void setPlayTime(int playTime) {
        this.playTime = playTime;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}
