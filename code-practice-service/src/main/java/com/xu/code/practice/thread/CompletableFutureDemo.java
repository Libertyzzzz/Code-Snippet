package com.xu.code.practice.thread;

import com.nextify.autoconfigure.ThreadPoolComponent;
import com.xu.code.practice.entity.GeneralEntity;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
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
        ExecutorService threadPool =threadPoolComponent.getThreadPoolExecutor();
        long startFuture = System.currentTimeMillis();
        Future<Long> future = threadPool.submit(() -> {
            return fibnacci(45);
        });
        Long res = future.get();
        System.out.println("计算结果:" + res + "" + "耗时:" + (System.currentTimeMillis() - startFuture));

        long startCompletableFuture = System.currentTimeMillis();
        List<Integer> shareList = new ArrayList<>();
        CompletableFuture<Long> completableFuture = CompletableFuture.supplyAsync(() -> {
            shareList.add(1);
            return fibnacci(45);
        }, threadPool);
        // 主线程睡眠，等待 CompletableFuture 完成
        // Thread.sleep(2000); // 在这里确保主线程等待一定时间以观察输出
        // completableFuture.join();
        System.out.println (shareList);
        completableFuture.thenAccept(r -> {
            System.out.println("计算结果:" + r + "" + "耗时:" + (System.currentTimeMillis() - startCompletableFuture));
        });

        threadPool.shutdown();

    }
    Integer res = 0;
    Callable<Integer> callable = Executors.callable(new Runnable() {
        @Override
        public void run() {

        }
    }, res);
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        completableFutureTest();
        CompletableFutureDemo completableFutureDemo = new CompletableFutureDemo();
        completableFutureDemo.compare();
    }
}
