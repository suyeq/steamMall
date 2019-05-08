package com.example.steam.vo;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-05-07
 * @time: 21:36
 */
public class CommentDetail {

    private String nickName;

    private String avatar;

    private String email;

    private double playTime;

    private int commmentNum;

    private int buyGames;

    private String content;

    private Date commentDate;

    private Integer zanNum;

    private Integer caiNum;

    private Integer recommendStatu;

    public CommentDetail(){}

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public double getPlayTime() {
        return playTime;
    }

    public void setPlayTime(double playTime) {
        this.playTime = playTime;
    }

    public int getCommmentNum() {
        return commmentNum;
    }

    public void setCommmentNum(int commmentNum) {
        this.commmentNum = commmentNum;
    }

    public int getBuyGames() {
        return buyGames;
    }

    public void setBuyGames(int buyGames) {
        this.buyGames = buyGames;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public Integer getZanNum() {
        return zanNum;
    }

    public void setZanNum(Integer zanNum) {
        this.zanNum = zanNum;
    }

    public Integer getCaiNum() {
        return caiNum;
    }

    public void setCaiNum(Integer caiNum) {
        this.caiNum = caiNum;
    }

    public Integer getRecommendStatu() {
        return recommendStatu;
    }

    public void setRecommendStatu(Integer recommendStatu) {
        this.recommendStatu = recommendStatu;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
