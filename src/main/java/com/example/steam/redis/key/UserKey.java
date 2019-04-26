package com.example.steam.redis.key;

import com.example.steam.redis.BaseKey;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-04-25
 * @time: 17:30
 */
public class UserKey extends BaseKey {

    private UserKey(){
        super();
    }

    public static UserKey USER_ID=new UserKey();

    public static UserKey COOKIE_ID=new UserKey();

}
