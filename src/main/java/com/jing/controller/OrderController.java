package com.jing.controller;

import com.jing.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: huangjingyan-200681
 * @Date: 2024/1/17 18:52
 * @Mail: huangjingyan@eastmoney.com
 * @Description:
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/generate")
    public String generateOrder(String userId,String packageNo){
        String orderId = orderService.generateOrder(userId,packageNo);
        return orderId;
    }
}
