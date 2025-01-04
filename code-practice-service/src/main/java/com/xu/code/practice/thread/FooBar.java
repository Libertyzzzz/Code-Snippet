package com.xu.code.practice.thread;

public class FooBar {

    /**
     * @Author liberty
     * @Date 2025/1/3 20:02
     */
    private int n;
    private volatile boolean flag = true;
    private final Object foo=  new Object(); // 锁标志
    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            synchronized(foo){
                while(!flag){
                    foo.wait();
                }
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                flag = false;
                foo.notify();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            synchronized(foo){
                while(flag){
                    foo.wait();
                }
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printBar.run();
                flag = true;
                foo.notify();
            }
        }
    }

    public static void main(String[] args) {
        int n = 2;  // 设置n，表示"foobar"的打印次数
        FooBar fooBar = new FooBar(n);

        // 创建线程A执行foo方法
        Thread threadA = new Thread(() -> {
            try {
                fooBar.foo(() -> System.out.print("foo"));  // 传入打印"foo"的任务
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 创建线程B执行bar方法
        Thread threadB = new Thread(() -> {
            try {
                fooBar.bar(() -> System.out.print("bar"));  // 传入打印"bar"的任务
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 启动线程
        threadA.start();
        threadB.start();

        // 等待线程执行完毕
        try {
            threadA.join();
            threadB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

}
