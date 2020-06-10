package com.teacher.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedssionConfig {

    @Value("${redssion.address}")
    private String address;

    /**
     * 装配客户端
     */
    @Bean
    RedissonClient redissonClient(){
        Config config = new Config();
        config.useSingleServer()
                .setAddress(address)
                .setDatabase(0)
                .setConnectionPoolSize(64);
        return Redisson.create(config);
    }

}
