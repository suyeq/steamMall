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

    private final static int FUTURED_KEY_TIME=30;

    public final static String FUTURED_KEY="futured";

    public final static String SPECIAL_INDEX_KEY="special_index";

    private final static int SPECIAL_KEY_TIME=30;

    private GameKey(int expiredTime){
        super(expiredTime);
    }

    public static GameKey FETURED_GAME=new GameKey(0);

    public static GameKey SPECIAL_INDEX_GAME=new GameKey(SPECIAL_KEY_TIME);
}
