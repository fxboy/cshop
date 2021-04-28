package cn.l404.pay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @auther Fanxing
 * 支付平台
 */
@EnableEurekaClient
@SpringBootApplication
public class PayApplication
{
    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class,args);
    }
}
