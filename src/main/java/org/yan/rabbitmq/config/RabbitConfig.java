package org.yan.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangxian
 * @date 2019/10/12 15:40
 */
@Configuration
public class RabbitConfig {
    @Bean
    public Queue initTestQueue(){
        return new Queue("test_queue",true);
    }
    @Bean
    public DirectExchange initTestDirectExchange(){
        return new DirectExchange("test_exchange");
    }

    @Bean
    public Binding initBinding(){
        return BindingBuilder.bind(initTestQueue()).to(initTestDirectExchange()).with("testBinding");
    }
}
