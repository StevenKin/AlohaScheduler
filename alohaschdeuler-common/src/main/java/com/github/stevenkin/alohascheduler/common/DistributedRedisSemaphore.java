package com.github.stevenkin.alohascheduler.common;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;

public class DistributedRedisSemaphore implements DistributedSemaphore{
    private String id;

    //许可证的数量
    private int permits;

    private RedisTemplate redisTemplate;

    private final String counterKey;

    private final String semaphoreKey;

    public DistributedRedisSemaphore(String id, int permits, RedisTemplate redisTemplate) {
        this.id = id;
        this.permits = permits;
        this.redisTemplate = redisTemplate;

        counterKey = Utils.buildKey(Constant.SEMAPHORE, id, Constant.COUNTER);
        semaphoreKey = Utils.buildKey(Constant.SEMAPHORE, id);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean tryAcquire() {
        Long score = redisTemplate.opsForValue().increment(counterKey, 1);
        String holder = SystemInfo.id + "-" +Thread.currentThread().getId();

        redisTemplate.opsForZSet().add(semaphoreKey, holder, score);
        Long rank = redisTemplate.opsForZSet().rank(semaphoreKey, holder);
        if (rank < permits) {
            return true;
        }
        release();
        return false;
    }

    @Override
    public void release() {
        String self = SystemInfo.id + "-" +Thread.currentThread().getId();
        redisTemplate.opsForZSet().remove(semaphoreKey, self);
    }

    @Override
    public void init(Map<String, Object> params) {
        redisTemplate.opsForValue().setIfAbsent(counterKey, "0");
    }

    @Override
    public void destroy() {
        redisTemplate.delete(counterKey);
        redisTemplate.delete(semaphoreKey);
    }
}
