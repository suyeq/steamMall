package com.example.steam;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.steam.dao")
public class SteamApplication{

    public static void main(String[] args) {
        SpringApplication.run(SteamApplication.class, args);
    }


}
