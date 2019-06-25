package com.iwanvi.bookstore.cdn.auth.comm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 配置属性（所有的配置文件属性都在这里加载）
 *
 * @author zzw
 * @since 2019年3月10日13:41:37
 */
@Component
public class ConfigProperties {

    //Redis配置属性-beg-
    @Value("${spring.redis1.database}")
    private int redis1DbIndex;
    @Value("${spring.redis1.host}")
    private String redis1Host;
    @Value("${spring.redis1.port}")
    private int redis1Port;
    @Value("${spring.redis1.password}")
    private String redis1Password;
    @Value("${spring.redis1.timeout}")
    private int redis1Timeout;
    @Value("${spring.redis1.jedis.pool.max-active}")
    private int redisPoolMaxActive;//连接池最大连接数（使用负值表示没有限制）
    @Value("${spring.redis1.jedis.pool.max-wait}")
    private int redisPoolMaxWait;//连接池最大阻塞等待时间（使用负值表示没有限制）
    @Value("${spring.redis1.jedis.pool.max-idle}")
    private int redisPoolMaxIdle;//连接池中的最大空闲连接
    @Value("${spring.redis1.jedis.pool.min-idle}")
    private int redisPoolMinIdle;//连接池中的最小空闲连接
    //Redis配置属性--------------end--------------



    public int getRedis1DbIndex() {
        return redis1DbIndex;
    }

    public String getRedis1Host() {
        return redis1Host;
    }

    public int getRedis1Port() {
        return redis1Port;
    }

    public String getRedis1Password() {
        return redis1Password;
    }

    public int getRedis1Timeout() {
        return redis1Timeout;
    }

    public int getRedisPoolMaxActive() {
        return redisPoolMaxActive;
    }

    public int getRedisPoolMaxWait() {
        return redisPoolMaxWait;
    }

    public int getRedisPoolMaxIdle() {
        return redisPoolMaxIdle;
    }

    public int getRedisPoolMinIdle() {
        return redisPoolMinIdle;
    }

}
