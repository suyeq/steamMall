package com.example.steam.vo;

import com.example.steam.entity.User;

/**
 * -----------
 * 穷则独善其身
 * 达则兼济天下
 * -----------
 *
 * @author: 苍术
 * @date: 2019-06-20
 * @time: 14:33
 */
public class UserVo extends User {

    private String avatarImage;

    public UserVo(){
        super();
    }

    public String getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(String avatarImage) {
        this.avatarImage = avatarImage;
    }

    @Override
    public String toString(){
        return avatarImage;
    }
}
