package com.jing.service;

import com.jing.constant.RedisDelayQueueEnum;
import com.jing.util.RedisDelayQueueUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: huangjingyan-200681
 * @Date: 2024/1/17 18:54
 * @Mail: huangjingyan@eastmoney.com
 * @Description:
 * @Version: 1.0
 **/
@Slf4j
@Service
public class OrderService {

    @Resource
    private RedisDelayQueueUtil redisDelayQueueUtil;

    public String generateOrder(String userId,String packageNo){
        Map<String,Object> orderInfo = new HashMap<>();
        String orderId = userId+DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS").format(LocalDateTime.now());

        orderInfo.put("orderId",orderId);
        orderInfo.put("packageNo",packageNo);
        orderInfo.put("userId",userId);
        log.info("模拟下单成功");

        //发送订单超时关闭通知
        redisDelayQueueUtil.push(orderInfo,30, TimeUnit.SECONDS, RedisDelayQueueEnum.ORDER_PAYMENT_TIMEOUT.getCode());
        return orderId;
    }
}
