package com.xu.code.practice.thread;

public class ProducerConsumerModel {

    /**
     * @Author liberty
     * @Date 2025/1/7 10:28
     */


    public synchronized void produce(){

    }

    public synchronized void consume(){

    }

    public static void main(String[] args) {
        ProducerConsumerModel model = new ProducerConsumerModel();
        new Thread(() -> {
            model.produce();
        }).start();

        new Thread(() -> {
            model.consume();
        }).start();
    }

}
