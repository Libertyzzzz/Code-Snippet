package com.xu.code.practice.thread;

public class PrintInOrderToThreeThreads {

    /**
     * @Author liberty
     * @Date 2024/10/16 22:02
     */

    public static void main(String[] args) {
        // 三个线程按序打印
        SharedPointer sharedPointer = new SharedPointer();
        Thread firstThread = new Thread(new PrintThread(sharedPointer, 1));
        Thread secondThread = new Thread(new PrintThread(sharedPointer, 2));
        Thread thirdThread = new Thread(new PrintThread(sharedPointer, 3));
        firstThread.start();
        secondThread.start();
        thirdThread.start();

        // 可以看到如果直接执行run()方法，只是一个正常的函数调用，会在主线程中同步执行这个方法,并不会异步开启线程去执行
        // 本质上就是一个普通的方法调用，和线程同步没有关系
        PrintThread thread = new PrintThread(sharedPointer, 1);
        thread.run();
    }

}
