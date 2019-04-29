package com.example.steam.redis;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-04-25
 * @time: 17:24
 */
public abstract class BaseKey implements RedisPrefixKey {

    protected String prefixKey;

    protected int expiredTime;

    public BaseKey(int expiredTime){
        this.prefixKey=getClass().getSimpleName();
        this.expiredTime=expiredTime;
    }

    public void setExpiredTime(int second){
        this.expiredTime=second;
    }

    @Override
    public String getThisPrefix() {
        return prefixKey+":";
    }

    @Override
    public int expiredTime() {
        return expiredTime;
    }
}
