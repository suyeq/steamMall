package com.example.steam.redis.key;

import com.example.steam.redis.BaseKey;

/**
 * @author: 苍术
 * @date: 2019-07-01
 * @time: 10:09
 */
public class FileKey extends BaseKey {


    public FileKey(int expiredTime) {
        super(expiredTime);
    }

    public static FileKey IMAGE_LIST=new FileKey(0);
}
