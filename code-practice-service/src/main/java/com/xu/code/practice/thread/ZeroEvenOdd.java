package com.xu.code.practice.thread;


import com.xu.code.practice.entity.IntConsumer;

public class ZeroEvenOdd {

    /**
     * @Author liberty
     * @Date 2025/1/3 21:22
     */

    private int n;
    private int flag = 0;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        synchronized(this){
            for (int i = 0; i < n; i++) {
                // 只有 num 是 0 时才打印 0
                while (flag != 0 ) {
                    wait();
                }
                printNumber.accept(0);
                flag = (i % 2 == 0) ? 1 : 2;
                notifyAll();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        synchronized(this){
            for (int i = 2; i <= n * 2; i += 2) {
                // 只有 num 是 0 时才打印 0
                while (flag != 2) {
                    wait();
                }
                printNumber.accept(i);
                flag = 0;
                notifyAll();
            }

        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        synchronized(this){
            for (int i = 1; i <= n * 2; i += 2) {
                // 只有 num 是 0 时才打印 0
                while (flag != 1) {
                    wait();
                }
                printNumber.accept(i);  // 打印 0
                flag = 0;
                notifyAll();
            }

        }
    }

    public static void main(String[] args) {
        ZeroEvenOdd lock = new ZeroEvenOdd(2);
        new Thread(() ->{
            try {
                lock.zero(new IntConsumer());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() ->{
            try {
                lock.even(new IntConsumer());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() ->{
            try {
                lock.odd(new IntConsumer());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
