package com.example.steam.entity;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-05-07
 * @time: 15:57
 */
@Component
public class Comment {

    private Long id;

    private String content;

    private Date commentDate;

    private String email;

    private Long gameId;

    private Integer zanNum;

    private Integer caiNum;

    private Integer recommendStatu;

    public Comment(){}

    public Comment(String content,String email,long gameId,int recommendStatu){
        this.content=content;
        this.commentDate=new Date();
        this.email=email;
        this.gameId=gameId;
        this.recommendStatu=recommendStatu;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
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
}
