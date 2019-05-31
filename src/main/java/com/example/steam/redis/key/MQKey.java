package com.example.steam.redis.key;

import com.example.steam.redis.BaseKey;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-05-30
 * @time: 11:20
 */
public class MQKey extends BaseKey {

    public MQKey(int expiredTime) {
        super(expiredTime);
    }

    public static MQKey MQ=new MQKey(0);
}
