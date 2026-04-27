package com.xu.code.practice.algorithm;

import org.omg.PortableServer.THREAD_POLICY_ID;

public class DuoDuo{

    static boolean flag = false;
    /**
     * @Author liberty
     * @Date 2024/11/11 20:39
     */
    private boolean available = false;
    public synchronized void produce(){
        while(available){
            try{
                wait();
            }catch (InterruptedException e){

            }
        }
        System.out.println("Producer: " + "produce item");
        available = true;
        notify();
    }

    public synchronized void consume(){
        while(!available){
            try{
                wait();
            }catch (InterruptedException e){

            }
        }
        System.out.println("Consumer: " + "consume item");
        available = false;
        notify();
    }
    public static void main(String[] args) {
        DuoDuo duo = new DuoDuo();
        Thread producer = new Thread(() -> {
            duo.produce();
        }, "Producer");
        Thread consumer = new Thread(() -> {
            duo.consume();
        }, "Consumer");
        producer.start();
        consumer.start();
    }

}
