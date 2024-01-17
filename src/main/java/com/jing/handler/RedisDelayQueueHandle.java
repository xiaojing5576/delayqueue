package com.jing.handler;

/**
 * @Author: huangjingyan-200681
 * @Date: 2024/1/17 17:16
 * @Mail: huangjingyan@eastmoney.com
 * @Description:
 * @Version: 1.0
 **/
public interface RedisDelayQueueHandle<T> {

    void execuete(T t);
}
