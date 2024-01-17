package com.jing.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: huangjingyan-200681
 * @Date: 2024/1/17 17:16
 * @Mail: huangjingyan@eastmoney.com
 * @Description:
 * @Version: 1.0
 **/
@Component("orderPaymentTimeout")
@Slf4j
public class OrderPaymentTimeout implements RedisDelayQueueHandle<Map> {


    @Override
    public void execuete(Map map) {
        log.info("处理订单超时支付消息：{}",map);
        //todo
    }
}
