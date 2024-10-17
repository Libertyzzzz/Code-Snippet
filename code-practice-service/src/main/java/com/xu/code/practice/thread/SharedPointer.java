package com.xu.code.practice.thread;

public class SharedPointer {

    /**
     * @Author liberty
     * @Date 2024/10/16 21:47
     */
    private int num = 1;
    private int  threadIdToRun = 1;

    public synchronized void print(int threadId){
        while (num <= 100) {
            while (threadId != threadIdToRun) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (num <= 100)
                System.out.println("CurrentThread-" + threadId + "-----" + "Print:" + num++);
            threadIdToRun = threadId % 3 + 1;
            // 唤醒其他线程
            notifyAll();
        }
    }

}
