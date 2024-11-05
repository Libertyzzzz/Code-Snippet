package com.xu.code.practice.thread;

public class DeadLockDemo {

    /**
     * @Author liberty
     * @Date 2024/11/2 13:04
     */
    private static SharedResource resource1 = new SharedResource();
    private static SharedResource resource2 = new SharedResource();
    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (resource1){
                System.out.println(Thread.currentThread() + "----" + "own resource1");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "----" + "wait resource2");
                synchronized ((resource2)){
                    System.out.println(Thread.currentThread() + "----" + "own resource2");
                }
            }
        }, "Thread-1").start();

        new Thread(() -> {
            synchronized (resource2){
                System.out.println(Thread.currentThread() + "----" + "own resource2");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "----" + "wait resource1");
                synchronized ((resource1)){
                    System.out.println(Thread.currentThread() + "----" + "own resource1");
                }
            }
        }, "Thread-2").start();

    }

}
