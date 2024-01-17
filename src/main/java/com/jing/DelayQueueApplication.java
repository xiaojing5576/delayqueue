package com.jing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @Author: huangjingyan-200681
 * @Date: 2024/1/17 18:51
 * @Mail: huangjingyan@eastmoney.com
 * @Description:
 * @Version: 1.0
 **/
@ServletComponentScan
@SpringBootApplication
public class DelayQueueApplication {

    public static void main(String[] args) {
        SpringApplication.run(DelayQueueApplication.class, args);
    }
}
