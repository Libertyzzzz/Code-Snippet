package com.xu.code.practice.thread;

public class PrintThread implements Runnable{

    /**
     * @Author liberty
     * @Date 2024/10/16 21:59
     */
    private final SharedPointer sharedPointer;
    private final int threadId;

    public PrintThread(SharedPointer sharedPointer, int threadId){
        this.sharedPointer = sharedPointer;
        this.threadId = threadId;
    }
    @Override
    public void run() {
        sharedPointer.print(threadId);
    }
}
