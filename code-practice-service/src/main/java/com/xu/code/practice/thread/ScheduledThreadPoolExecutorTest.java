package com.xu.code.practice.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExecutorTest {

    /**
     * @Author liberty
     * @Date 2025/1/6 16:06
     */
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor scheduledThreadPool = new NextifyScheduledThreadPool(2, 4);
        ScheduledFuture<?> future = scheduledThreadPool.scheduleAtFixedRate(() -> {
            // task 执行逻辑
            System.out.println("定时每隔五秒执行一次");

        }, 5, 5, TimeUnit.SECONDS);

        scheduledThreadPool.schedule(() -> {
            future.cancel(false);
            System.out.println("定时任务停止");
            scheduledThreadPool.shutdown();
        }, 10, TimeUnit.SECONDS);
    }

}
