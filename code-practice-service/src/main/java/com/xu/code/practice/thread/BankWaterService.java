package com.xu.code.practice.thread;

import com.nextify.autoconfigure.ThreadPoolComponent;

import java.util.Map;
import java.util.concurrent.*;

public class BankWaterService implements Runnable{

    /**
     * @Author liberty
     * @Date 2025/1/5 11:17
     */

    /**
     *  创建拦截四个线程的屏障，处理完之后执行当前类的run方法
     */
    private CyclicBarrier barrier = new CyclicBarrier(4, this);

    /**
     * 假设银行流水一共涉及四个sheet,所以启动四个线程
     */
    private ExecutorService threadPool = Executors.newFixedThreadPool(4);

    /**
     * 保存每个线程计算出的银行流水
     */
    private ConcurrentHashMap<String, Integer>  bankWaterSheetMap = new ConcurrentHashMap<>();

    private void count(){
        for(int i = 0; i < 4; i++){
            threadPool.execute(() ->{
                // 计算逻辑
                // 这里假设每个流水都为1
                bankWaterSheetMap.put(Thread.currentThread().getName(), 1);
                // 计算完屏障前等待
                try {
                    barrier.await();
                } catch (InterruptedException e) {

                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @Override
    public void run() {
        int res = 0;
        for(Map.Entry<String, Integer> sheet : bankWaterSheetMap.entrySet()){
            res += sheet.getValue();
        }
        System.out.println(res);
    }

    public static void main(String[] args) {
        BankWaterService bankWaterService = new BankWaterService();
        bankWaterService.count();
    }
}
