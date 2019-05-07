package com.example.steam.service;

import com.example.steam.dao.SystemNeedDao;
import com.example.steam.entity.SystemNeed;
import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.SystemNeedKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-05-06
 * @time: 22:41
 */
@Service
public class SystemNeedService {

    @Autowired
    SystemNeedDao systemNeedDao;

    @Autowired
    RedisService redisService;

    public SystemNeed findSystemNeedById(long id){
        SystemNeed systemNeed=redisService.get(SystemNeedKey.SYSTEMNEED_ID,id+"",SystemNeed.class);
        if (systemNeed!=null){
            return systemNeed;
        }
        systemNeed=systemNeedDao.findSystemNeedById(id);
        redisService.set(SystemNeedKey.SYSTEMNEED_ID,id+"",systemNeed);
        return systemNeed;
    }
}
