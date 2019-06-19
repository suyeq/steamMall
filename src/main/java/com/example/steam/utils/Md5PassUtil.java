package com.example.steam.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created with IntelliJ IDEA.
 * MD5加密工具
 * @author: Suyeq
 * @date: 2019-04-25
 * @time: 17:02
 */
public class Md5PassUtil {

    private final static String salt="1q2w3e";

    public static String firstMd5(String password){
        String camouflagePassword=""+salt.charAt(0)+salt.charAt(4)+password+salt.charAt(5)+salt.charAt(2);
        return DigestUtils.md5Hex(camouflagePassword);
    }

    public static String secondMd5(String password,String salt){
        String camouflagePassword=""+salt.charAt(0)+salt.charAt(4)+password+salt.charAt(5)+salt.charAt(2);
        return DigestUtils.md5Hex(camouflagePassword);
    }

    public static String md5Conver(String password,String salt){
        return secondMd5(firstMd5(password),salt);
    }

//    public static void main(String args[]){
//        System.out.println(md5Conver("473721601","1q2w3e"));
//    }
//
}
