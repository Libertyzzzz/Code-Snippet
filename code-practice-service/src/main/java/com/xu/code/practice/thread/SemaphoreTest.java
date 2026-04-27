package com.xu.code.practice.thread;

import com.nextify.autoconfigure.ThreadPoolComponent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    /**
     * @Author liberty
     * @Date 2025/1/5 11:32
     */

    /**
     * Semaphore 用来控制访问临界资源的线程数量
     * 临界资源：是指在并发环境下，当多个线程访问该资源时，可能会导致冲突或数据不一致的共享资源
     */
    private static final int THREAD_COUNT = 30;
    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);
    private static Semaphore semaphore = new Semaphore(5);
    public static void main(String[] args) {
        for(int i = 0; i < THREAD_COUNT; i++){
            threadPool.execute(() ->{
                try {
                    semaphore.acquire(2);
                    Thread.sleep(3000);
                    System.out.println("Grant permission, allow execution");
                    semaphore.release();
                    //System.out.println("Release permission");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        threadPool.shutdown();
    }

}
