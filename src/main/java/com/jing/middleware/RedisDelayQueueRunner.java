package com.jing.middleware;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.jing.constant.RedisDelayQueueEnum;
import com.jing.handler.RedisDelayQueueHandle;
import com.jing.util.RedisDelayQueueUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: huangjingyan-200681
 * @Date: 2024/1/17 17:18
 * @Mail: huangjingyan@eastmoney.com
 * @Description:
 * @Version: 1.0
 **/
@Slf4j
@Component
public class RedisDelayQueueRunner implements CommandLineRunner {

    @Resource
    private RedisDelayQueueUtil redisDelayQueueUtil;

    @Resource
    private ApplicationContext applicationContext;

    @Resource
    private Map<String,RedisDelayQueueHandle> handleMap;

    @Resource
    private ThreadPoolTaskExecutor ptask;

    ThreadPoolExecutor executorService = new ThreadPoolExecutor(3,5,30, TimeUnit.SECONDS,
            new LinkedBlockingDeque<Runnable>(1000),new ThreadFactoryBuilder().setNameFormat("order-delay-%d").build());


    @Override
    public void run(String... args) throws Exception {
        ptask.execute(()->{
            while(true){
                try{
                    RedisDelayQueueEnum[] queueEnums = RedisDelayQueueEnum.values();
                    for(RedisDelayQueueEnum queueEnum:queueEnums){
                        Object value = redisDelayQueueUtil.getDelayQueue(queueEnum.getCode());
                        if(value != null){
//                            RedisDelayQueueHandle<Object> redisDelayQueueHandle = (RedisDelayQueueHandle<Object>)applicationContext.getBean(queueEnum.getBeanId());
                            RedisDelayQueueHandle<Object> redisDelayQueueHandle = handleMap.get(queueEnum.getBeanId());
                            executorService.execute(()-> redisDelayQueueHandle.execuete(value));

                        }
                    }
                    TimeUnit.MILLISECONDS.sleep(500L);
                } catch (InterruptedException e) {
                    log.error("Redisson延迟队列监测异常中断：{}",e);
                }
            }
        });
        log.info("Redisson延迟队列监测启动成功......");
    }
}
