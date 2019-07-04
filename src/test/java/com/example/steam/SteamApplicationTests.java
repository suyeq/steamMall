package com.example.steam;

import com.example.steam.entity.SystemNeed;
import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.SensitiveKey;
import com.example.steam.redis.key.UserKey;
import com.example.steam.service.SensitiveWordService;
import com.example.steam.service.SystemNeedService;
import com.example.steam.service.TypeService;
import com.example.steam.utils.SensitiveWordUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SteamApplication.class)
public class SteamApplicationTests {
    @Autowired
    TypeService typeService;
    @Autowired
    RedisService redisService;
    @Autowired
    SystemNeedService systemNeedService;

    @Autowired
    SensitiveWordUtil sensitiveWordUtil;

    @Autowired
    SensitiveWordService sensitiveWordService;
//    @Test
//    public void contextLoads() {
//    }

    @Test
    public void test() throws IOException {
        String index="你是个大傻子傻瓜";
        sensitiveWordService.addSensitiveWord("傻子");
        sensitiveWordService.addSensitiveWord("傻子傻瓜");
        System.out.println(sensitiveWordUtil.replaceSensitiveWord(index,"SteamMall"));
    }

    @Test
    public void testSystemNeed(){
        SystemNeed systemNeed=new SystemNeed("1","1","1","1","1","1","1","1");
        systemNeedService.addSystemNeed(systemNeed);
        System.out.println(systemNeed.getId());
    }
}
