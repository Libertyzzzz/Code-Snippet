package com.xu.code.practice.algorithm;

import java.util.HashMap;
import java.util.Map;

public class SharedPointer {

    /**
     * @Author liberty
     * @Date 2024/11/22 14:14
     */
    private int toRunThreadId = 1;
    private int num = 1;
    private int boundary;

    public SharedPointer(int toRunThreadId, int num, int boundary){
        this.toRunThreadId = toRunThreadId;
        this.num = num;
        this.boundary = boundary;
    }

    public synchronized void print(int threadId){
        while(num <= boundary){
            while (threadId != toRunThreadId){
                try {
                    wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            if(num <= 100)
                System.out.println("CurrentThread:" + threadId + "-----" + "print" + ":" + num++);
            toRunThreadId  = toRunThreadId % 3 + 1;
            notifyAll();
        }

    }

    private Map<Integer, Character> charsMap = new HashMap<Integer, Character>(){{
        put(1, 'A');
        put(2, 'B');
        put(3, 'C');
    }};
    public synchronized void printAToC(int threadId){
        while (num <= boundary){
            while(threadId !=  toRunThreadId){
                try {
                    wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            if(num <= boundary){
                System.out.println("CurrentThread:" + threadId + "-----" + "print" + ":" + charsMap.get(threadId));
                num++;
            }
            toRunThreadId = toRunThreadId % 3 + 1;
            notifyAll();
        }


    }

}
