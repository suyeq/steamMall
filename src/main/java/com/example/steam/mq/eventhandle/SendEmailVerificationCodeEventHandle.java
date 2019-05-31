package com.example.steam.mq.eventhandle;

import com.example.steam.mq.Event;
import com.example.steam.mq.EventType;
import com.example.steam.utils.EmailUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * 验证码邮件
 * @author: Suyeq
 * @date: 2019-05-29
 * @time: 22:39
 */
@Component
public class SendEmailVerificationCodeEventHandle implements EventHandle {

    @Override
    public void eventHandle(Event event, ApplicationContext applicationContext) {
        EmailUtil emailUtil=(EmailUtil)applicationContext.getBean("emailUtil");
        emailUtil.sendVerificationCode((String)event.getEtrMsg().get(Event.EMAIL));
    }

    @Override
    public List<EventType> bindEventType() {
        return Arrays.asList(EventType.SEND_EMAIL_VERIFICATION_CODE);
    }
}
