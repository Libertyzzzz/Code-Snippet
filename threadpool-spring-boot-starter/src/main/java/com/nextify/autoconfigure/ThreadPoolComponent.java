package com.nextify.autoconfigure;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class ThreadPoolComponent {

    /**
     * @Author liberty
     * @Date 2024/11/8 12:16
     */
    @Bean
    public ExecutorService getThreadPoolExecutor(){
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                4,
                200,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2)
        );
        return  threadPool;
    }

}
