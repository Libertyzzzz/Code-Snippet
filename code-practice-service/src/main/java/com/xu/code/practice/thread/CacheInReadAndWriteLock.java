package com.xu.code.practice.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheInReadAndWriteLock {

    /**
     * @Author liberty
     * @Date 2025/1/11 15:02
     */

    /**
     * 读写锁使用示例
     */
    static Map<String, Object> map = new HashMap<>();
    static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock writeLock = readWriteLock.writeLock();
    static Lock readLock = readWriteLock.readLock();

    public static final Object get(String key){
        readLock.lock();
        try {
            return map.get(key);
        }finally {
            readLock.unlock();
        }
    }

    //  更新操作时必须获取写锁，保证后续的读写操作阻塞
    public static final Object pur(String key, Object value){
        writeLock.lock();
        try {
            return map.put(key, value);
        }finally {
            writeLock.unlock();
        }
    }

    //  更新操作时必须获取写锁，保证后续的读写操作阻塞
    public static void clean(){
        writeLock.lock();
        try {
            map.clear();
        }finally {
            writeLock.unlock();
        }
    }


}
