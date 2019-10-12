package org.yan.rabbitmq.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author wangxian
 * @date 2019/10/12 17:03
 */
@Service
@RabbitListener(queues ="test_queue" )
public class RabbitListenerService {
    @RabbitHandler
    public void process(String hello) throws JsonProcessingException {
        System.out.println("监听消息");
        System.out.println(new ObjectMapper().writeValueAsString(hello));
        System.out.println("消费完成");

    }
}
