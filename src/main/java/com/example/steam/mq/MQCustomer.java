package com.example.steam.mq;


import com.example.steam.mq.eventhandle.EventHandle;
import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.MQKey;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * 消息消费者
 * @author: Suyeq
 * @date: 2019-05-29
 * @time: 20:37
 */
@Service
public class MQCustomer implements InitializingBean, DisposableBean {

    Logger log= LoggerFactory.getLogger(MQCustomer.class);

    @Autowired
    RedisService redisService;

    @Autowired
    ApplicationContext applicationContext;

    ThreadPoolExecutor threadPoolExecutor=null;
    /**
     * 存贮事件类型到事件处理器的映射
     */
    Map<EventType,List<EventHandle>> eventMap=new HashMap<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        int coreThread=Runtime.getRuntime().availableProcessors();
        threadPoolExecutor=new ThreadPoolExecutor(coreThread,coreThread*2,3, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>());
        Map<String,EventHandle> eventHandleMap=applicationContext.getBeansOfType(EventHandle.class);
        for (Map.Entry<String,EventHandle> entry:eventHandleMap.entrySet()) {
            List<EventType> typeList=entry.getValue().bindEventType();
            for (int i=0;i<typeList.size();i++){
                List<EventHandle> handleList=eventMap.get(typeList.get(i));
                if (handleList==null){
                    handleList=new LinkedList<>();
                }
                if (!handleList.contains(entry.getValue())){
                    handleList.add(entry.getValue());
                }
                eventMap.put(typeList.get(i),handleList);
            }
        }
        //System.out.println(eventMap.toString());
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                while (true){
                    Event event=redisService.rpop(MQKey.MQ,Event.EVENT_KEY,Event.class);
                    if (event==null){
                        continue;
                    }
                    if (!eventMap.keySet().contains(event.getEventType())){
                        log.error("未知的事件类型");
                        continue;
                    }
                    for (EventHandle eventHandle:eventMap.get(event.getEventType())){
                        threadPoolExecutor.execute(new EventHanleThread(event,eventHandle,applicationContext));
                    }
                    /**
                     * 每隔500毫秒取一次
                     */
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        log.error("I/O异常");
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });
    }

    @Override
    public void destroy() throws Exception {
        threadPoolExecutor.shutdown();
    }


    class EventHanleThread implements Runnable{

        Event event;

        EventHandle eventHandle;

        ApplicationContext applicationContext;

        EventHanleThread(Event event, EventHandle eventHandle, ApplicationContext applicationContext){
            this.event=event;
            this.eventHandle = eventHandle;
            this.applicationContext=applicationContext;
        }

        @Override
        public void run() {
            eventHandle.eventHandle(event,applicationContext);
        }
    }



}
