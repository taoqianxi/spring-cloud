package com.teacher.util;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisLockUtil {

    @Autowired
    private  RedissonClient redissonClient;

    private static String LOCK_NAME="lock001";

    public  RLock lock(String lockName) {
        return redissonClient.getFairLock(lockName);
    }

    public  void unlock(RLock lock){
        lock.unlock();
    }

    public  void unlock(String lockName){
        redissonClient.getFairLock(lockName).unlock();
    }
    /**
     * 带超时的锁
     * @param lockName
     * @param timeout 超时时间   单位：秒
     */
    public  RLock lock(String lockName, int timeout) {
        RLock lock = redissonClient.getFairLock(lockName);
        lock.lock(timeout, TimeUnit.SECONDS);
        return lock;
    }


}
