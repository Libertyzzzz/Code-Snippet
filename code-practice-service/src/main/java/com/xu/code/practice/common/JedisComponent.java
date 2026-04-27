package com.xu.code.practice.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

@Configuration
public class JedisComponent {

    /**
     * @Author liberty
     * @Date 2024/10/17 16:52
     */
    @Bean
    public Jedis getJedisClient(){
        return new Jedis("localhost", 6379);
    }



}
