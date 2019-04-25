package com.example.steam.service;

import com.example.steam.dao.UserDao;
import com.example.steam.entity.User;
import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.UserKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-04-25
 * @time: 16:58
 */
@Service
public class UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    RedisService redisService;

    public User findByEmail(String email){
        //User user=redisService.get(UserKey.USER_OBJECT,)
        return userDao.findByEmail(email);
    }



}
