package com.example.steam.utils;

import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-04-25
 * @time: 20:47
 */
public class ResultMsg {

    public static ResultMsg LOGIN_SUCCESS=new ResultMsg("201","登陆成功");

    public static ResultMsg NO_EMAIL=new ResultMsg("501","该邮箱不存在");

    public static ResultMsg PASS_ERROR=new ResultMsg("502","密码错误");


    private String code;

    private Object msg;

    private ResultMsg(String code,Object msg){
        this.code=code;
        this.msg=msg;
    }

    public static ResultMsg SUCCESS(Object object){
        return new ResultMsg("200",object);
    }

    public static ResultMsg FAILURE(Object object){
        return new ResultMsg("500",object);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }
}
