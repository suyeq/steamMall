package com.example.steam;

import com.example.steam.entity.SystemNeed;
import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.UserKey;
import com.example.steam.service.SystemNeedService;
import com.example.steam.service.TypeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SteamApplication.class)
public class SteamApplicationTests {
    @Autowired
    TypeService typeService;
    @Autowired
    RedisService redisService;
    @Autowired
    SystemNeedService systemNeedService;
//    @Test
//    public void contextLoads() {
//    }

    @Test
    public void test(){
        redisService.set(UserKey.USER_ID,"test","hhahahah");
        System.out.println(redisService.get(UserKey.USER_ID,"test",String.class));
    }

    @Test
    public void testSystemNeed(){
        SystemNeed systemNeed=new SystemNeed("1","1","1","1","1","1","1","1");
        systemNeedService.addSystemNeed(systemNeed);
        System.out.println(systemNeed.getId());
    }
}
