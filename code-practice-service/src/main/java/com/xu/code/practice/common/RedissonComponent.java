package com.xu.code.practice.common;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonComponent {

    /**
     * @Author liberty
     * @Date 2024/10/17 17:53
     */
    @Bean
    public RedissonClient getRedissonClient(){
        Config config = new Config();
        return Redisson.create(config );
    }

}
