package com.jing.util;

import org.redisson.api.RBlockingDeque;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Author: huangjingyan-200681
 * @Date: 2024/1/17 16:28
 * @Mail: huangjingyan@eastmoney.com
 * @Description:
 * @Version: 1.0
 **/
@Component
public class RedisDelayQueueUtil {

    @Resource
    private RedissonClient redissonClient;

    /**
     * 推送数据
     * @param value     推送内容
     * @param delay     延时时长
     * @param timeUnit  时长单位
     * @param queueCode 队列标识code
     * @param <T>
     * @return
     */
    public <T> boolean push(T value, long delay, TimeUnit timeUnit,String queueCode){
        if(!StringUtils.hasText(queueCode) || delay < 0 || value == null){
            return false;
        }
        try {
            //redisson的阻塞队列
            RBlockingDeque<Object> blockingDeque = redissonClient.getBlockingDeque(queueCode);
            //redisson的延时队列
            RDelayedQueue<Object> delayedQueue = redissonClient.getDelayedQueue(blockingDeque);
            delayedQueue.offer(value,delay,timeUnit);

        }catch (Exception e){
            return false;
        }
        return true;
    }


    /**
     * 获取延时队列中的元素
     * @param queueCode
     * @param <T>
     * @return
     */
    public <T> T getDelayQueue(String queueCode){
        if(!StringUtils.hasText(queueCode)){
            return null;
        }
        RBlockingDeque<Map> blockingQueue = redissonClient.getBlockingDeque(queueCode);
        //获取队列中的第一个元素
        T value = (T)blockingQueue.poll();
        return value;
    }


    public boolean removeDelayedQueue(Object o,String queueCode){
        if(!StringUtils.hasText(queueCode) || Objects.isNull(o)){
            return false;
        }
        RBlockingDeque<Object> blockingDeque = redissonClient.getBlockingDeque(queueCode);
        RDelayedQueue<Object> delayedQueue = redissonClient.getDelayedQueue(blockingDeque);
        return delayedQueue.remove(o);
    }
}
