package com.phenix.starter.redis.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author chihiro.zhang
 */
@Slf4j
public class RedisClient {

    private RedisTemplate redisTemplate;

    public RedisClient(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void set(final String key, final String value) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set(key, value);
    }

    /**
     * '
     * 指定失效时间，单位秒
     *
     * @param key
     * @param value
     * @param timeOut
     */
    public void set(final String key, final String value, long timeOut) {
        this.set(key, value);
        redisTemplate.expire(key, timeOut, TimeUnit.SECONDS);
    }

    public String get(final String key) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    /**
     * 设置分布式锁
     *
     * @param key
     * @param value
     * @return
     */
    public synchronized Boolean setNX(final String key, final String value) throws Exception {
        Object obj = null;
        try {
            obj = redisTemplate.execute((RedisCallback<Object>) connection -> {
                StringRedisSerializer serializer = new StringRedisSerializer();
                Boolean success = connection.setNX(serializer.serialize(key), serializer.serialize(value));
                connection.close();
                return success;
            });
        } catch (Exception e) {
            log.error("setNX redis error, key : {} - {}", key, e);
            throw e;
        }
        return obj != null ? (Boolean) obj : false;
    }

    /**
     * 设置分布式锁，超时间单位秒
     *
     * @param key
     * @param value
     * @return
     */
    public synchronized Boolean setNX(final String key, final String value, long timeOut) throws Exception {
        boolean b = this.setNX(key, value);
        if (b) {
            redisTemplate.expire(key, timeOut, TimeUnit.SECONDS);
        }
        return b;
    }

    /**
     * 删除锁
     *
     * @param key
     * @return
     */
    public void unlock(final String key) {
        redisTemplate.delete(key);
    }


}
