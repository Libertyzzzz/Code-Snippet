package com.xu.code.practice.proxy;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

public class HeapSizeView {

    /**
     * @Author liberty
     * @Date 2024/11/1 12:57
     */
    public static void main(String[] args) {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();

        System.out.println("Heap Memory Usage:");
        System.out.println("Initial: " + heapMemoryUsage.getInit() / (1024 * 1024) + " MB");
        System.out.println("Used: " + heapMemoryUsage.getUsed() / (1024 * 1024) + " MB");
        System.out.println("Max: " + heapMemoryUsage.getMax() / (1024 * 1024) + " MB");
    }

}
