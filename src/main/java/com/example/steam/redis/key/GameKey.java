package com.example.steam.redis.key;

import com.example.steam.redis.BaseKey;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-04-28
 * @time: 14:07
 */
public class GameKey extends BaseKey {

    public final static String FUTURED_KEY="futured";

    private GameKey(){
        super();
    }

    public static GameKey FETURED_GAME=new GameKey();
}
