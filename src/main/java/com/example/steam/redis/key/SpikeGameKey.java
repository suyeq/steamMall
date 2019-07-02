package com.example.steam.redis.key;

import com.example.steam.redis.BaseKey;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-05-17
 * @time: 22:23
 */
public class SpikeGameKey extends BaseKey {

    public static final String SPIKE_STOCK_KEY="stock:";

    public static final String RANDM_PATH_KEY="path:";

    public static final String SPIKE_TIMES_KEY="times:";

    private static final int MAX_EXSIS_tIME=1000*30;

    private SpikeGameKey(int expiredTime) {
        super(expiredTime);
    }

    public final static SpikeGameKey SPIKE_ID=new SpikeGameKey(0);

    public final static SpikeGameKey SPIKE_STOCK=new SpikeGameKey(0);

    public final static SpikeGameKey RANDM_PATH=new SpikeGameKey(MAX_EXSIS_tIME);

    public final static SpikeGameKey SPIKE_TIMES=new SpikeGameKey(60);
}
