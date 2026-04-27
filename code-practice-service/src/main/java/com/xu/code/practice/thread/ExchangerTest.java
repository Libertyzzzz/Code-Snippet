package com.xu.code.practice.thread;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangerTest {

    /**
     * @Author liberty
     * @Date 2025/1/5 12:01
     */

    /**
     * Exchanger用于线程间的数据交换，他提供一个同步点， 当两个线程到达该同步点时通过exchange()交换彼此的数据
     * 如果一个线程先到达同步点执行了exchange()方法，他会一直到 等待第二个线程到达同步点
     * 当两个线程都到达时才会进行交换     *
     */
    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);
    private static final Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        threadPool.execute(() ->{
            String A = "Bank statement of A";
            try {
                String receivedData = exchanger.exchange(A);
                System.out.println("A:" + receivedData);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadPool.execute(() -> {
            String B = "Bank statement of B";
            try {
                String receivedData = exchanger.exchange(B);
                System.out.println("B:" + receivedData);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadPool.shutdown();
    }


}
