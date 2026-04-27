package com.xu.code.practice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "spring.redis")
@Component
@Data
public class RedisClientConfig {

    /**
     * @Author liberty
     * @Date 2024/11/6 13:44
     */
    private String username = "root";
    private String password = "";
    private String host = "localhost";
    private String port = "6379";

}
