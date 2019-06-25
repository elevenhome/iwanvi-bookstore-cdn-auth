package com.iwanvi.bookstore.cdn.auth.comm.config.redis;

import com.iwanvi.bookstore.tools.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * redis操作类
 */
@Component
public class RedisComponentOne {

    private final static Logger logger = LoggerFactory.getLogger(RedisComponentOne.class);

    @Resource(name = "redisTemplateOne")
    private RedisTemplate redisTemplate;

    @Resource(name = "stringRedisTemplateOne")
    private RedisTemplate stringRedisTemplate;


    private ValueOperations valueOperations;
    private RedisConnectionFactory redisConnectionFactory;

    @PostConstruct
    private void inInit() {
        valueOperations = redisTemplate.opsForValue();
        redisConnectionFactory = redisTemplate.getConnectionFactory();
    }

    public String getString(String key) {
        return  (String)valueOperations.get(key);

    }

    public <T> T getCache(String key) {
        if (Utils.isEmpty(key)) {
            return null;
        }
        return (T)valueOperations.get(key);
    }

    public void setCache(String key, Object value) {
        if (Utils.isEmpty(key) || Utils.isEmpty(value)) {
            return;
        }
        valueOperations.set(key, value);
    }


    public Boolean removeCache(String key) {
        if (Utils.isEmpty(key)) {
            return false;
        }
        return redisTemplate.delete(key);
    }

    /**
     * 添加缓存 设置默认时间
     * @param key
     * @param value
     * @param time
     */
    public void setExpireCache(final String key, final Object value, long time) {
        if (Utils.isEmpty(key) || Utils.isEmpty(value)) {
            return;
        }
        if (Utils.isEmpty(time)) {
            this.setExpireCacheDefault(key, value);
        } else {
            valueOperations.set(key, value, time, TimeUnit.SECONDS);
        }

    }

    /**
     *  添加缓存并设置默认时间
     * @param key
     * @param value
     */
    public void setExpireCacheDefault(final String key, final Object value) {
        if (Utils.isEmpty(key) || Utils.isEmpty(value)) {
            return;
        }
        valueOperations.set(key, value, RedisConstant.Expire.MINUTE_TEN, TimeUnit.SECONDS);
    }

    /**
     * 现获取在累加
     * @param key
     * @return
     */
    public Long getAndIncrement(String key) {
        //从redis获取ID
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisConnectionFactory);
        return entityIdCounter.getAndIncrement();
    }


    /**
     * 先累加在获取
     * @param key
     * @return
     */
    public Long incrementAndGet(String key) {
        //从redis获取ID
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisConnectionFactory);
        return entityIdCounter.incrementAndGet();
    }

}
