package com.example.steam.mq;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * 封装的事件
 * @author: Suyeq
 * @date: 2019-05-29
 * @time: 21:22
 */
public class Event {

    public final static String EVENT_KEY="eventhandle";

    public final static String EMAIL="email";

    public final static String SPIKE="spike";

//    public final static String SPIKE_ID="spikeId";

//    public final static String USER_ID="userId";

    private EventType eventType;
    /**
     * 存贮信息
     */
    private Map<String,String> etrMsg=new HashMap<>();

    public Event(){}

    public Event(EventType eventType){
        this.eventType=eventType;
    }

    public Event setEtrMsg(String key, String value){
        etrMsg.put(key,value);
        return this;
    }

    public Map getEtrMsg(){
        return etrMsg;
    }


    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }
}
