package com.example.steam.aop;

import com.example.steam.config.DynamicDataSourceHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-04-25
 * @time: 15:23
 */
@Component
@Aspect
public class DynamicDataSourceAop {

    /**
     * 切面service包下的所有以add形式开头的方法
     */
    @Before("execution(* com.example.steam.service.*.add*(..))")
    public void dynamicDataSourceAddMethod(){
        DynamicDataSourceHolder.putDataSource(DynamicDataSourceHolder.MASTER);
    }

    @Before("execution(* com.example.steam.service.*.update*(..))")
    public void dynamicDataSourceUpdateMethod(){
        DynamicDataSourceHolder.putDataSource(DynamicDataSourceHolder.MASTER);
    }

    @Before("execution(* com.example.steam.service.*.delete*(..))")
    public void dynamicDataSourceDeleteMethod(){
        DynamicDataSourceHolder.putDataSource(DynamicDataSourceHolder.MASTER);
    }

    @Before("execution(* com.example.steam.service.*.find*(..))")
    public void dynamicDataSourceFindMethod(){
        DynamicDataSourceHolder.putDataSource(DynamicDataSourceHolder.SLAVE);
    }

}
