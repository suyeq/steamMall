package com.example.steam.redis.key;

import com.example.steam.redis.BaseKey;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-04-26
 * @time: 14:55
 */
public class CookieKey extends BaseKey {

    private CookieKey(){
        super();
    }

    public static CookieKey EMAIL=new CookieKey();

}
