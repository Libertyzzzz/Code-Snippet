package com.xu.code.practice.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExecutorTest {

    /**
     * @Author liberty
     * @Date 2025/1/6 16:06
     */
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor scheduledThreadPool = new ScheduledThreadPoolExecutor(4);
        scheduledThreadPool.scheduleAtFixedRate(() -> {
            // task 执行逻辑

        }, 5, 5, TimeUnit.MICROSECONDS);
    }

}
