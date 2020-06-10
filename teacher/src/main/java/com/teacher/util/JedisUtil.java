package com.teacher.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class JedisUtil {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public void get(){
        redisTemplate.opsForValue().set("k1","v1");
        System.out.println(redisTemplate.opsForValue().get("k1"));
    }

}
