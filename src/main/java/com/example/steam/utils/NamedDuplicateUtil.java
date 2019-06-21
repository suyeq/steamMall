package com.example.steam.utils;

/**
 * 解决命名重复工具
 * @author: 苍术
 * @date: 2019-06-21
 * @time: 14:17
 */
public class NamedDuplicateUtil {

    public static String nameConversion(String name){
        int index=0;
        String newName=null;
        if (name.lastIndexOf("(")!=-1){
            index=name.lastIndexOf("(");
            char number=name.charAt(index+1);
            int num=Integer.parseInt(number+"")+1;
            newName=name.substring(0,index+1)+num+name.substring(index+2);
            System.out.println(newName );
            return newName;
        }else {
            index=name.lastIndexOf(".");
            newName=name.substring(0,index)+"(1)"+name.substring(index);
            System.out.println(newName);
            return newName;
        }
    }

}
