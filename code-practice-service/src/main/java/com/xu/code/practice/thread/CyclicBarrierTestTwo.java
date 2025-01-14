package com.xu.code.practice.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTestTwo {

    /**
     * @Author liberty
     * @Date 2025/1/5 10:34
     */
    // BarrierAction 提供了一个高级构造函数，CyclicBarrier(int parties, Runnable barrierAction)
    // 用于在给定线程都到达屏障点时，优先执行该线程任务 barrierAction
    static CyclicBarrier barrier = new CyclicBarrier(2, new Action());

    public static void main(String[] args) {
        // 从执行结果可以看出，当异步线程和主线程调用await()到达屏障点后会优先执行Action中的任务，打印
        new Thread(() -> {
            try {
                barrier.await();
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println(1);
        }).start();
        // 主线程
        try{
            barrier.await();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(2);
    }

    static class Action implements Runnable{
        @Override
        public void run() {
            System.out.println("Execute this action with priority");
        }
    }

}
