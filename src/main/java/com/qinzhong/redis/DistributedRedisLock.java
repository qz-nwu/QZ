package com.qinzhong.redis;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

/*
 * 分布式锁：SET NX + 过期时间
 * */
@Component
public class DistributedRedisLock {

    private final StringRedisTemplate stringRedisTemplate;

    public DistributedRedisLock(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /*
     * 尝试加锁
     * */
    public boolean tryLock(String key, Duration ttl) {
        Boolean ok = stringRedisTemplate.opsForValue().setIfAbsent(key, "1", ttl);
        return Boolean.TRUE.equals(ok);
    }

    /*
     * 释放锁
     * */
    public void unlock(String key) {
        stringRedisTemplate.delete(key);
    }
}
