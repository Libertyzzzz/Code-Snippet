package com.xu.code.practice.algorithm;

import com.nextify.autoconfigure.ThreadPoolComponent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PrintThread implements Runnable{

    /**
     * @Author liberty
     * @Date 2024/11/22 14:21
     */
    private int threadId;
    private SharedPointer sharedPointer;

    // ReentranLock 实现线程轮流打印ABC
    private static final ReentrantLock lock = new ReentrantLock();
    private static final int PRINT_COUNT = 5;
    private static final Condition conditionFirst = lock.newCondition();
    private static final Condition conditionSecond = lock.newCondition();
    private static final Condition conditionThird = lock.newCondition();
    private static int state = 0;
    private static int num = 0;

    public PrintThread(int threadId, SharedPointer sharedPointer){
        this.threadId = threadId;
        this.sharedPointer = sharedPointer;
    }

    @Override
    public void run() {
        // sharedPointer.print(threadId);
        sharedPointer.printAToC(threadId);
    }

    // ReentrantLock 实现轮流打印ABC
    public static void print(String name, int targetState, Condition currentCondition, Condition nextCondition){
        for (int i = 0; i < PRINT_COUNT; i++) {
            lock.lock();
            try {
                while(state != targetState){
                    currentCondition.await();
                }
                if(num < PRINT_COUNT){
                    System.out.println("CurrentThreadId" + targetState +  "---" + name);
                    num++;
                }

                state = (state + 1) % 3;
                nextCondition.signal();
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }finally {
                lock.unlock();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException{
        SharedPointer sharedPointer = new SharedPointer(1, 1, 100);
        Thread firstThread = new Thread(new PrintThread(1, sharedPointer), "FirstThread");
        Thread secondThread = new Thread(new PrintThread(2, sharedPointer), "SecondThread");
        Thread thirdThread = new Thread(new PrintThread(3, sharedPointer), "ThirdThread");

//        Thread firstThread = new Thread(new PrintThread(1, sharedPointer), "FirstThread");
//        Thread secondThread = new Thread(new PrintThread(2, sharedPointer), "SecondThread");
//        Thread thirdThread = new Thread(new PrintThread(3, sharedPointer), "ThirdThread");
        firstThread.start();
        secondThread.start();
        thirdThread.start();
        new Thread(() -> print("A", 0, conditionFirst, conditionSecond)).start();
        new Thread(() -> print("B", 1, conditionSecond, conditionThird)).start();
        new Thread(() -> print("C", 2, conditionThird, conditionFirst)).start();
        PrintThread printThread = new PrintThread(1, new SharedPointer(1, 1, 100));
        printThread.run();


    }
}
