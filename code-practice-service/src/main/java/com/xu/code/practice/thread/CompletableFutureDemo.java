package com.xu.code.practice.thread;

import com.xu.code.practice.common.ThreadPoolComponent;
import com.xu.code.practice.entity.GeneralEntity;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.*;

@Component
public class CompletableFutureDemo {

    @Resource
    private ThreadPoolComponent threadPoolComponent;
    @Resource
    private CompletableFutureDemo completableFutureDemo;

    /**
     * @Author liberty
     * @Date 2024/10/12 15:07
     */

    public static void completableFutureTest(){
        ExecutorService threadPols = new ThreadPoolExecutor(
                2,
                4,
                200,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2)
        );
        CompletableFuture<GeneralEntity> future = CompletableFuture.supplyAsync(() -> {
            GeneralEntity curr = new GeneralEntity();
            curr.setKey(1);
            curr.setValue("1");
            return curr;
        }, threadPols);
        future.thenApply(res -> {
            System.out.println(res);
            return res;
        }).thenApply(res -> {
             res.setKey(2);
             res.setValue("2");
             return res;
        }).thenAccept(res -> {
            System.out.println(res);
        });

    }

    public long fibnacci(int i){
        if(i <= 1)
            return i;
        return  fibnacci(i - 1) + fibnacci(i - 2);
    }

    public static void futureTest(){
        ExecutorService threadPools = new ThreadPoolExecutor(
                2,
                4,
                200,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2)
        );
        Future<?> future = threadPools.submit(() -> {

        });
    }

    public void compare() throws ExecutionException, InterruptedException {
        ExecutorService threadPool =threadPoolComponent.getThreadPool();
        long startFuture = System.currentTimeMillis();
        Future<Long> future = threadPool.submit(() -> {
            return fibnacci(45);
        });
        Long res = future.get();
        System.out.println("计算结果:" + res + "" + "耗时:" + (System.currentTimeMillis() - startFuture));

        long startCompletableFuture = System.currentTimeMillis();
        final long[] mainThreadRes = {0};
        CompletableFuture<Long> completableFuture = CompletableFuture.supplyAsync(() -> {
            for(int i = 0; i < 5; i++){
                ++mainThreadRes[0];
            }
            return fibnacci(45);
        }, threadPool);
        System.out.println(mainThreadRes[0]);
        completableFuture.thenAccept(r -> {
            System.out.println("计算结果:" + r + "" + "耗时:" + (System.currentTimeMillis() - startCompletableFuture));
        });
        // 主线程睡眠，等待 CompletableFuture 完成
        Thread.sleep(20); // 在这里确保主线程等待一定时间以观察输出

        threadPool.shutdown();

    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        completableFutureTest();
        CompletableFutureDemo completableFutureDemo = new CompletableFutureDemo();
        completableFutureDemo.compare();
    }
}
