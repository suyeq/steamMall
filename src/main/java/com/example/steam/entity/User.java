package com.example.steam.entity;

import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-04-25
 * @time: 16:52
 */
@Component
public class User {

    public final static int ISADMIN=1;

    private Long id;

    private String nickName;

    private String salt;

    private String email;

    private String password;

    private Long avatar;

    private double playTime;

    private int commentNum;

    private int buyGames;

    private int isAdmin;

    private int lv;

    private String country;

    private String province;

    private String introduction;

    public User(){}

    public User(String nickName,String email,String salt,String password){
        this.nickName=nickName;
        this.email=email;
        this.salt=salt;
        this.password=password;
        this.avatar=1L;
        this.playTime=0;
        this.buyGames=0;
        this.commentNum=0;
        this.isAdmin=0;
        this.lv=0;
        this.country="China";
        this.province="Hunan";
        this.introduction="这个人很懒，什么都没写~";
    }

    public User(long id,String nickName,String salt,String email,String password,
                long avatar,int playTime,int buyGames,int commentNum,int isAdmin,
                int lv,String country,String province,String introduction){
        this.id=id;
        this.nickName=nickName;
        this.email=email;
        this.salt=salt;
        this.password=password;
        this.avatar=avatar;
        this.playTime=playTime;
        this.buyGames=buyGames;
        this.commentNum=commentNum;
        this.isAdmin=isAdmin;
        this.lv=lv;
        this.country=country;
        this.province=province;
        this.introduction=introduction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getAvatar() {
        return avatar;
    }

    public void setAvatar(Long avatar) {
        this.avatar = avatar;
    }

    public double getPlayTime() {
        return playTime;
    }

    public void setPlayTime(double playTime) {
        this.playTime = playTime;
    }

    public int getBuyGames() {
        return buyGames;
    }

    public void setBuyGames(int buyGames) {
        this.buyGames = buyGames;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
