package com.example.steam.vo;

import com.example.steam.entity.Image;
import com.example.steam.entity.User;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-04-27
 * @time: 14:08
 */
@Component
public class LoginUser {

    private Long id;

    private String nickName;

    private String email;

    private String avatar;

    private int isAdmin;

    public LoginUser(){}

    public LoginUser(User user, Image image){
        this.id=user.getId();
        this.email=user.getEmail();
        this.nickName=user.getNickName();
        this.isAdmin=user.getIsAdmin();
        this.avatar=image.getUrl();
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

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString(){
        return "[id="+id+",email="+email+"]";
    }
}
