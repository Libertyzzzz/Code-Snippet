package com.xu.code.practice.algorithm;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Solution {

    /**
     * @Author liberty
     * @Date 2024/12/11 13:13
     */
    private static final int PRINT_COUNT = 5;
    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition conditionFirst = lock.newCondition();
    private static final Condition conditionSecond = lock.newCondition();
    private static final Condition conditionThird = lock.newCondition();
    private static int state = 0;

    public static void print( String name, int targetState, Condition currentCondition, Condition nextCondition){
        for(int i = 0; i < PRINT_COUNT; i++){
            lock.lock();
            try{
                while(targetState != state){
                    currentCondition.await();
                }
                System.out.println("CurrentThread" + "-----" + name);
                state = (state + 1) % 3;
            }catch(InterruptedException e){

            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {

    }

}
