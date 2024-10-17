package com.xu.code.practice.thread;

public class ThreadCommunications {

    /**
     * @Author liberty
     * @Date 2024/10/15 22:44
     */


    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                sharedResource.produce();
                // 模拟生产
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                // SharedResource sharedResource = new SharedResource();
                sharedResource.consume();
                // 模拟消费
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        producer.start();
        consumer.start();
    }

}
