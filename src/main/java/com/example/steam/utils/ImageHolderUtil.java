package com.example.steam.utils;

import com.example.steam.entity.Image;

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 图片用户容器
 * @author: 苍术
 * @date: 2019-06-29
 * @time: 19:39
 */
public class ImageHolderUtil {

    private final static ThreadLocal<List<Image>> images=new ThreadLocal<>();

    private final static Lock lock=new ReentrantLock();

    public static  List<Image> getImageHolder(){
        lock.lock();
        List<Image> list=images.get();
        lock.unlock();
        return list;
    }

    public static void setImageHolder(List<Image> imageList) {
        images.set(imageList);
    }
}
