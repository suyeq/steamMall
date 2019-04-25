package com.example.steam.redis;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-04-25
 * @time: 17:22
 */
public interface RedisPrefixKey {

    String getThisPrefix();

    int expiredTime();

}
