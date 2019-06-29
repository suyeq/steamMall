package com.example.steam.utils;

import com.example.steam.entity.Image;

import java.util.List;
import java.util.Map;

/**
 * 图片用户容器
 * @author: 苍术
 * @date: 2019-06-29
 * @time: 19:39
 */
public class ImageHolderUtil {

    private final static ThreadLocal<List<Image>> images=new ThreadLocal<>();

    public static List<Image> getImageHolder(){
        return images.get();
    }

    public static void setImageHolder(List<Image> imageList) {
        images.set(imageList);
    }
}
