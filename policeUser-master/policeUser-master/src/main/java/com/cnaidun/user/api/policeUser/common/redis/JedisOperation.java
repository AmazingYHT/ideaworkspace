package com.cnaidun.user.api.policeUser.common.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class JedisOperation {

    public static final String PREFIX_USER = "USER_PLOICE";


    @Autowired
    RedisTemplate<String, String> redisTemplate;

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, String value) {
        boolean result = false;
        try {
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            operations.set(PREFIX_USER + key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存设置时效时间
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, String value, Long expireTime, TimeUnit timeUnit) {
        boolean result = false;
        try {
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            operations.set(PREFIX_USER + key, value);
            redisTemplate.expire(PREFIX_USER + key, expireTime, timeUnit);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(PREFIX_USER + key);
        }
    }

    /**
     * 批量删除key
     *
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<String> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0) {
            redisTemplate.delete(PREFIX_USER + keys);
        }
    }

    /**
     * 删除对应的value
     *
     * @param key
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(PREFIX_USER + key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(PREFIX_USER + key);
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public String getRedis(String key) {
        String result = null;
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        result = operations.get(PREFIX_USER + key);
        return result;
    }
}

