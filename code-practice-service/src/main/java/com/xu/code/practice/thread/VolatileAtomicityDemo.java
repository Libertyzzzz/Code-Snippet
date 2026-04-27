package com.xu.code.practice.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VolatileAtomicityDemo {

    /**
     * @Author liberty
     * @Date 2024/10/17 22:21
     */
    private volatile static int num = 0;

    public void increase(){
        num++;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPools = Executors.newFixedThreadPool(5);
        VolatileAtomicityDemo atomicityDemo = new VolatileAtomicityDemo();
        for (int i = 0; i < 5; i++) {
            threadPools.submit(() -> {
                for (int j = 0; j < 500; j++) {
                    atomicityDemo.increase();
                }

            });
        }
        Thread.sleep(3000);
        System.out.println(num);
        threadPools.shutdown();

    }
}
