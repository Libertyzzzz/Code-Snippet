package com.xu.code.practice.redis;

import org.redisson.RedissonDelayedQueue;
import org.redisson.api.RDelayedQueue;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.Collections;

@Component
public class DistributedLock {

    /**
     * @Author liberty
     * @Date 2024/10/17 16:30
     */
    private String script = "if redis.call('get', KEYS[1]) == ARGV[1] then " +
            "return redis.call('del', KEYS[1]) " +
            "else return 0 end";
    private static final String LOCK_KEY = "lock_key";
    private static final long EXPIRE_TIME = 2000;
    @Resource
    private Jedis jedis;

    public long tryLock(String lockValue){
        // 使用setnx命令
        long setnx = jedis.setnx(LOCK_KEY, lockValue);
        return setnx;

    }

    public void unlock(String lockValue){

            // 使用 Lua 脚本确保只有持有锁的线程才能释放锁            
            jedis.eval(script, Collections.singletonList(LOCK_KEY), Collections.singletonList(lockValue));

    }

}
