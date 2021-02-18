package com.github.stevenkin.alohascheduler.common;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;

public class RedisMessageSerialNoGenerator implements MessageSerialNoGenerator{
    private RedisTemplate redisTemplate;

    private final String key = Utils.buildKey(Constant.SERIAL_NO);

    public RedisMessageSerialNoGenerator(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Long get() {
        return redisTemplate.opsForValue().increment(key);
    }

    @Override
    public void init(Map<String, Object> params) {
        redisTemplate.opsForValue().set(key, 0);
    }

    @Override
    public void destroy() {
        redisTemplate.delete(key);
    }
}
