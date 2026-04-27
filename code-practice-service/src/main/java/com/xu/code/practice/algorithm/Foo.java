package com.xu.code.practice.algorithm;

import java.util.concurrent.CountDownLatch;

public class Foo {

    /**
     * @Author liberty
     * @Date 2024/12/10 21:19
     */
    private CountDownLatch secondLatch = new CountDownLatch(1); // 控制第二步执行
    private CountDownLatch thirdLatch = new CountDownLatch(1); // 控制第三步执行

    public Foo() {
    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        // 第一步完成后，唤醒第二步
        secondLatch.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        // 等待第一步完成
        secondLatch.await();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        // 第二步完成后，唤醒第三步
        thirdLatch.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        // 等待第二步完成
        thirdLatch.await();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }


}
