package com.xu.code.practice.thread;

public class SharedResource {

    /**
     * @Author liberty
     * @Date 2024/10/15 22:46
     */

    private boolean available = false;

    public  synchronized void produce() {
        while (available) {
            try {
                wait();  // 资源可用，生产者等待
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Produced item");
        available = true;
        notify();  // 唤醒消费者线程
    }

    public synchronized void consume() {
        while (!available) {
            try {
                wait();  // 没有资源可用，消费者等待
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Consumed item");
        available = false;
        notify();  // 唤醒生产者线程
    }

    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();
        Thread producer = new Thread(() -> {
            sharedResource.produce();
        });
        Thread consumer = new Thread(() ->{
            sharedResource.consume();
        });
        producer.start();
        consumer.start();
    }

}
