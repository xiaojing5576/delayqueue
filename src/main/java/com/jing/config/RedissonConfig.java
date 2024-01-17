package com.jing.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: huangjingyan-200681
 * @Date: 2024/1/17 16:17
 * @Mail: huangjingyan@eastmoney.com
 * @Description:
 * @Version: 1.0
 **/
@Configuration
public class RedissonConfig {


    private final String REDISSON_PREFIX = "redis://";
    private final RedisProperties redisProperties;


    public RedissonConfig(RedisProperties redisProperties) {
        this.redisProperties = redisProperties;
    }

    public RedissonClient redissionClient(){
        Config config = new Config();
        String url = REDISSON_PREFIX + redisProperties.getHost()+ ":"+redisProperties.getPort();
        config.useSingleServer()
                .setAddress(url)
                .setPassword(redisProperties.getPassword())
                .setDatabase(redisProperties.getDatabase())
                .setPingConnectionInterval(2000);
        config.setLockWatchdogTimeout(10000L);
        try {
            return Redisson.create(config);
        }catch (Exception e){
            return null;
        }

    }
}
