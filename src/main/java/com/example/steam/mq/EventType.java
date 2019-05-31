package com.example.steam.mq;

/**
 * Created with IntelliJ IDEA.
 * 事件类型
 * @author: Suyeq
 * @date: 2019-05-29
 * @time: 22:44
 */
public enum  EventType {

    /**
     * 发送验证码事件类型
     */
    SEND_EMAIL_VERIFICATION_CODE("发送验证码");

    private String value;

    EventType(String value){
        this.value=value;
    }

    public String getValue(){
        return value;
    }
}
