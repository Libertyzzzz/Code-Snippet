package com.xu.code.practice.thread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {

    /**
     * @Author liberty
     * @Date 2025/1/12 13:07
     */
    private static final Queue<Integer> queue = new LinkedList<>();
    private static final int MAX_CAPACITY = 5;
    private static  Thread producerThread;
    private static  Thread consumerThread;



    public static void main(String[] args) {

        producerThread = new Thread(() -> {
            for(int i = 0; i < 10; i++){
                synchronized (queue) {
                    while (queue.size() == MAX_CAPACITY)
                        // LockSupport.park();
                    {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }



                    // 生成数据
                    queue.offer(i);
                    System.out.println("Produce: " + i);
                    // 唤醒消费者消费
                    // LockSupport.unpark(consumerThread);
                    queue.notify();
                }

            }
        });

        consumerThread = new Thread(() -> {
            for(int i = 0; i < 10; i++){
                synchronized (queue){
                    while(queue.isEmpty())
                        //LockSupport.park();
                    {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    int item = queue.poll();
                    System.out.println("Consume: " + item);
                    LockSupport.unpark(producerThread);
                    queue.notify();
                }
            }
        });

        producerThread.start();
        consumerThread.start();
    }


}
