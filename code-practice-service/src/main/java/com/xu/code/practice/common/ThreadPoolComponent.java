package com.xu.code.practice.common;

import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class ThreadPoolComponent {

    /**
     * @Author liberty
     * @Date 2024/10/12 16:11
     */
    public static ExecutorService getThreadPool(){
        ExecutorService threadPools = new ThreadPoolExecutor(
                2,
                4,
                200,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2)
        );
        return threadPools;
    }

}
