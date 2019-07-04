package com.example.steam.redis.key;

import com.example.steam.redis.BaseKey;

/**
 * @author: 苍术
 * @date: 2019-07-04
 * @time: 19:39
 */
public class SensitiveKey extends BaseKey {

    public final static String SENSITIVE_KEY="sensiword:";

    public SensitiveKey(int expiredTime) {
        super(expiredTime);
    }

    public final static SensitiveKey WORD_LIST=new SensitiveKey(0);

}
