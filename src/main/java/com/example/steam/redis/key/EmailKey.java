package com.example.steam.redis.key;

import com.example.steam.redis.BaseKey;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-05-30
 * @time: 21:40
 */
public class EmailKey extends BaseKey {

    private final static int VERIFICATION_CODE_TIME=90;

    public EmailKey(int expiredTime) {
        super(expiredTime);
    }

    public final static EmailKey VERIFICATION_CODE=new EmailKey(VERIFICATION_CODE_TIME);
}
