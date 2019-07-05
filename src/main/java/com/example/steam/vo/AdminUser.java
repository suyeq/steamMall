package com.example.steam.vo;

import org.springframework.stereotype.Component;

/**
 * @author: 苍术
 * @date: 2019-07-05
 * @time: 9:39
 */
@Component
public class AdminUser {

    private Long id;

    private String nickName;

    private String email;

    public AdminUser(){}


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

    @Override
    public String toString(){
        return "[id="+id+",email="+email+",nickName="+nickName+"]";
    }
}
