package com.jing.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Author: huangjingyan-200681
 * @Date: 2024/1/17 17:12
 * @Mail: huangjingyan@eastmoney.com
 * @Description:
 * @Version: 1.0
 **/

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum RedisDelayQueueEnum {
    ORDER_PAYMENT_TIMEOUT("ORDER_PAYMENT_TIMEOUT","支付超时，自动取消订单","orderPaymentTimeout")
    ;

    private String code;

    private String name;

    private String beanId;
}
