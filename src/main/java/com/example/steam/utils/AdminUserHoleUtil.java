package com.example.steam.utils;

import com.example.steam.entity.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 管理员用户容器
 * @author: 苍术
 * @date: 2019-06-26
 * @time: 19:55
 */
public class AdminUserHoleUtil {

//    private final static ThreadLocal<AdminUserHolder> adminUsers=new ThreadLocal<>();

    private final static Map<String,AdminUserHolder> adminMap=new HashMap<>();

    public static User getUser(String email){
        if (adminMap.get(email) == null){
            return null;
        }
        return adminMap.get(email).getUser();
    }

    public static void addUser(User user){
        adminMap.put(user.getEmail(),new AdminUserHolder(user));
    }

    public static void removeUser(String email){
        adminMap.remove(email);
    }

    static class AdminUserHolder{
        long startTime;
        long expiredTime;
        User user;

        AdminUserHolder(User user){
            this.startTime=System.currentTimeMillis();
            this.expiredTime=60L*60*2*1000;
            this.user=user;
        }

        User getUser(){
            long now=System.currentTimeMillis();
            if (now-this.startTime>this.expiredTime){
                System.out.println("啦啦啦");
                return null;
            }
            return this.user;
        }


    }

}
