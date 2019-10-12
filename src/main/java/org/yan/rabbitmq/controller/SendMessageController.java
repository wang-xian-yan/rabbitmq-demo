package org.yan.rabbitmq.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author wangxian
 * @date 2019/10/12 15:46
 */
@RequestMapping("/api/messages")
@RestController
public class SendMessageController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    public ResponseEntity<?> sendMessage() throws JsonProcessingException {
        String messageId = UUID.randomUUID().toString();
        String data = "第一条消息";
        String sendTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> request = new HashMap(3);
        request.put("messageId", messageId);
        request.put("data", data);
        request.put("sendTime", sendTime);
        rabbitTemplate.convertAndSend("test_exchange", "testBinding", new ObjectMapper().writeValueAsString(request));
        return ResponseEntity.ok(request);

    }
}
