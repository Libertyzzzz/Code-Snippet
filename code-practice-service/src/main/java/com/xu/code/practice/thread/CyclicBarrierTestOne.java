package com.xu.code.practice.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTestOne {

    /**
     * @Author liberty
     * @Date 2025/1/5 10:34
     */
    static CyclicBarrier barrier = new CyclicBarrier(2);

    public static void main(String[] args) {
        // 这里因为主线程和子线程的调度是由CPU决定的，两个线程都有可能先执行
        // 所以会有两种可能输出
        new Thread(() -> {
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(1);
        }).start();

        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(2);
    }

}
