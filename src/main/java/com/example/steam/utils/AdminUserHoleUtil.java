package com.example.steam.utils;

import com.example.steam.entity.User;
import org.springframework.stereotype.Component;

/**
 * 管理员用户容器
 * @author: 苍术
 * @date: 2019-06-26
 * @time: 19:55
 */
public class AdminUserHoleUtil {

    private final static ThreadLocal<AdminUserHolder> adminUsers=new ThreadLocal<>();

    public static User getUser(){
        if (adminUsers.get()==null){
            return null;
        }
        return adminUsers.get().getUser();
    }

    public static void addUser(User user){
        adminUsers.set(new AdminUserHolder(user));
    }

    static class AdminUserHolder{
        long startTime;
        long expiredTime;
        User user;

        AdminUserHolder(User user){
            this.startTime=System.currentTimeMillis();
            this.expiredTime=60*60*2*1000;
            this.user=user;
        }

        User getUser(){
            long now=System.currentTimeMillis();
            if (now-this.startTime>this.expiredTime){
                return null;
            }
            return this.user;
        }


    }

}
