package com.example.steam.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 日志打印
 * @author: 苍术
 * @date: 2019-06-26
 * @time: 18:37
 */
@Aspect
@Component
@Order(1)
public class LogInformationAspect {

    Logger log= LoggerFactory.getLogger(LogInformationAspect.class);

    @Around("execution(* com.example.steam.controller.*.*(..))")
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature=joinPoint.getSignature();
        String methodName=signature.getName();
        log.info("--------"+methodName+"方法开始"+"--------");
        Object object=joinPoint.proceed();
        log.info("--------"+methodName+"方法结束"+"--------");
        return object;
    }

}
