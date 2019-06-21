package com.example.steam.utils;

import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.EmailKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * 邮件服务
 * @author: Suyeq
 * @date: 2019-05-30
 * @time: 15:58
 */
@Service
public class EmailUtil {

    private final static String VERIFICATION_CODE="你的验证码是：";

    private final static String FIND_PASSWORD="系统将你的密码修改为：";

    Logger log= LoggerFactory.getLogger(EmailUtil.class);

    @Autowired
    private JavaMailSenderImpl javaMailSender;
    @Autowired
    private RedisService redisService;

    @Value("${spring.mail.username}")
    private String emialSend;


    /**
     * 发送一封邮件
     * @param receiveEmail
     * @param content
     */
    private void sendMessage(String receiveEmail,String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emialSend);
        message.setTo(receiveEmail);
        message.setSubject("来自steam的邮件");
        message.setText(content);
        javaMailSender.send(message);
        log.info("邮件发送完毕");
    }

    /**
     * 发送验证码邮件并将验证码存入缓存
     * @param receiveEmail
     */
    public void sendVerificationCode(String receiveEmail){
        String randomCode= UUIDUtil.randomUUID().substring(0,5);
        redisService.set(EmailKey.VERIFICATION_CODE,receiveEmail,randomCode);
        sendMessage(receiveEmail,VERIFICATION_CODE+randomCode);
    }

    /**
     * 发送修改后的密码
     * @param receiveEmail
     * @param newPassword
     */
    public void sendFindPassword(String receiveEmail,String newPassword){
        sendMessage(receiveEmail,FIND_PASSWORD+newPassword);
    }

//    public static void main(String args[]){
//        System.out.println(UUIDUtil.randomUUID().replaceAll("-","").substring(0,5));
//    }




}
