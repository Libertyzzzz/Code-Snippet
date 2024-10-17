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
    }

}
