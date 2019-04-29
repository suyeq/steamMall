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

    private final static int expireTime=7200;

    private CookieKey(int expireTime){
        super(expireTime);
    }

    public static CookieKey EMAIL=new CookieKey(expireTime);

}
