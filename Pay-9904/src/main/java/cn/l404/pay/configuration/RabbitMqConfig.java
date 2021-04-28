package cn.l404.pay.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @auther Fanxing
 * RabbitMQ的配置
 */
@Configuration
public class RabbitMqConfig {
    @Bean
    public Queue getQueuePay() {
        return new Queue("pay");
    }

    @Bean
    public Queue getQueueOrder() {
        return new Queue("order");
    }

    @Bean
    DirectExchange payOrderExchange() {
        return new DirectExchange("PayOrderExchange",true,false);
    }

    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(getQueuePay()).to(payOrderExchange()).with("rout");
    }

}
