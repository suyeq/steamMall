package com.example.steam;

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
    @Test
    public void contextLoads() {
    }

    @Test
    public void test(){
        System.out.println(typeService.findAllTyp().toString());
    }

}
