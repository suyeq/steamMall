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

    public BaseKey(){
        this.prefixKey=getClass().getSimpleName();
        this.expiredTime=0;
    }

    public void setExpiredTime(int second){
        this.expiredTime=expiredTime;
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
