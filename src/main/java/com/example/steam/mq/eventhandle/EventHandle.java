package com.example.steam.mq.eventhandle;

import com.example.steam.mq.Event;
import com.example.steam.mq.EventType;
import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * 事件处理器
 * @author: Suyeq
 * @date: 2019-05-29
 * @time: 22:38
 */
public interface EventHandle {

    /**
     * 事件处理
     * @param event
     * @param applicationContext
     */
    void eventHandle(Event event, ApplicationContext applicationContext);

    /**
     * 绑定的事件类型
     * @return
     */
    List<EventType> bindEventType();
}
