package com.xu.code.practice.common;

import com.nextify.autoconfigure.ThreadPoolComponent;
import com.xu.code.practice.config.RedisClientConfig;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.redisson.config.TransportMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Configuration
@Slf4j
public class RedissonComponent {
    /**
     * @Author liberty
     * @Date 2024/10/17 17:53
     */
    @Resource
    private RedisClientConfig redisClientConfig;
    private String address;

    @Resource
    private ThreadPoolComponent threadPoolComponent;

    @PostConstruct
    public void init(){
        this.address = "redis://" + redisClientConfig.getHost() + ":" + redisClientConfig.getPort();
        log.info("Redis address: " + address);
    }

    @Bean(destroyMethod = "shutdown")
    public RedissonClient getRedissonClient(){
        Config config = new Config();
        config.setCodec(StringCodec.INSTANCE);
        config.setTransportMode(TransportMode.NIO);

        SingleServerConfig singleServerConfig  = config.useSingleServer()
                .setAddress(address)
                .setDatabase(0);
        return Redisson.create(config );
    }

}
