package com.example.steam.redis.key;

import com.example.steam.redis.BaseKey;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-05-07
 * @time: 8:19
 */
public class SystemNeedKey extends BaseKey {

    public SystemNeedKey(int expiredTime) {
        super(expiredTime);
    }

    public final static SystemNeedKey SYSTEMNEED_ID=new SystemNeedKey(0);
}
