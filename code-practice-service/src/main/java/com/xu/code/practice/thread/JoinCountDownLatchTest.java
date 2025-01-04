package com.xu.code.practice.thread;

import java.util.concurrent.CountDownLatch;

public class JoinCountDownLatchTest {

    /**
     * @Author liberty
     * @Date 2025/1/3 16:31
     */
    static CountDownLatch countDownLatch = new CountDownLatch(2);
    public static void main(String[] args) throws InterruptedException {
        Thread parser1 = new Thread(() -> {
            System.out.println("parser1 finish");
        });
        Thread parser2 = new Thread(() -> {
            System.out.println("parser2 finish");
        });
        parser1.start();
        parser2.start();
        parser1.join(); // 等待解析器1完成
        parser2.join(); // 等待解析器2完成
        System.out.println("all parser finish ");

        new Thread(() ->{
            System.out.println(1);
            countDownLatch.countDown();
            System.out.println(2);
            countDownLatch.countDown();
        }).start();
        countDownLatch.await();
        System.out.println(3);
    }

}
