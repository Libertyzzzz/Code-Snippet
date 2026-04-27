package com.xu.code.practice.thread;

import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueTest {

    /**
     * @Author liberty
     * @Date 2025/1/14 16:22
     */
    
    static class Producer implements Runnable {
        SynchronousQueue<Integer> queue;
        public Producer(SynchronousQueue<Integer> queue){
            this.queue = queue;
        }
        @Override
        public void run() {
            for(int i = 0; i < 5; i++){
                System.out.println("Produce: " + i);
                try {
                    queue.put(i);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    static class Consumer implements Runnable {
        SynchronousQueue<Integer> queue;
        public Consumer(SynchronousQueue<Integer> queue){
            this.queue = queue;
        }
        @Override
        public void run() {
            for(int i = 0; i < 5; i++){
                System.out.println("Consume: " + i);
                try {
                    queue.take();
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public static void main(String[] args) {
        SynchronousQueue<Integer> queue = new SynchronousQueue<>();
        Thread producer = new Thread(new Producer(queue));
        Thread consumer = new Thread(new Consumer(queue));
        producer.start();
        consumer.start();
        System.out.println((double) 1 / 3 );
        StringBuilder curr = new StringBuilder();
        curr.append("ab");
        curr.append("cd");
        curr.delete(2, 4);
        System.out.println(curr.toString());


    }

}
