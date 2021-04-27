package cn.l404.shoppingcart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @auther Fanxing
 * SpringBoot快捷创建启动类
 */
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class ShoppingCartApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShoppingCartApplication.class, args);
    }
}    