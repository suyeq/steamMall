package com.example.steam.mq.eventhandle;

import com.example.steam.mq.Event;
import com.example.steam.mq.EventType;
import com.example.steam.utils.EmailUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * -----------
 * 穷则独善其身
 * 达则兼济天下
 * -----------
 *
 * @author: 苍术
 * @date: 2019-06-19
 * @time: 17:19
 */
@Component
public class FindPasswordHandle implements EventHandle{

    @Override
    public void eventHandle(Event event, ApplicationContext applicationContext) {
        String email=(String) event.getEtrMsg().get(Event.EMAIL);
        String newPassword=(String) event.getEtrMsg().get(Event.NEW_PASSWORD);
        ((EmailUtil)applicationContext.getBean("emailUtil")).sendFindPassword(email,newPassword);
    }

    @Override
    public List<EventType> bindEventType() {
        return Arrays.asList(EventType.FIND_PASSWORD);
    }
}
