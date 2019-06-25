package com.iwanvi.bookstore.cdn.auth.comm.config.redis;

import com.iwanvi.bookstore.cdn.auth.comm.ConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;


/**
 * 配置redis实例（连接中600Redis，新环境）
 *
 * @author zzw
 * @since 2019年1月11日21:27:03
 */
@Configuration
@EnableCaching
public class RedisConfigOne extends RedisConfig {


    @Resource
    private transient ConfigProperties configProperties;

    /**
     * 配置redis连接工厂
     */
    @Primary
    @Bean
    public RedisConnectionFactory cacheRedisConnectionFactory() {
        return createJedisConnectionFactory(configProperties.getRedis1DbIndex(),
                configProperties.getRedis1Host(),
                configProperties.getRedis1Port(),
                configProperties.getRedis1Password());
    }

    /**
     * 配置redisTemplate 注入方式使用@Resource(name="") 方式注入
     */
    @Bean(name = "redisTemplateOne")
    public RedisTemplate cacheRedisTemplate() {
        RedisTemplate template = new RedisTemplate();
        template.setConnectionFactory(cacheRedisConnectionFactory());
        setSerializer(template);
        template.afterPropertiesSet();
        return template;
    }


    @Bean(name = "stringRedisTemplateOne")
    public StringRedisTemplate stringRedisTemplate() {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(cacheRedisConnectionFactory());
        setSerializer(template);
        template.afterPropertiesSet();
        return template;
    }

}
