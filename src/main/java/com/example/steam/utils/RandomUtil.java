package com.example.steam.utils;

import java.util.Random;

/**
 * @author: 苍术
 * @date: 2019-07-05
 * @time: 11:23
 */
public class RandomUtil {

    private final static Random random=new Random();

    public static int randomSeed(int seed){
        return random.nextInt(seed)+1;
    }
}
