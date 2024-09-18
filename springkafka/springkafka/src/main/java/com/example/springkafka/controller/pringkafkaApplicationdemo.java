package com.example.springkafka.controller;

import com.example.springkafka.service.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api")
public class pringkafkaApplicationdemo  {

    @Autowired
    private Producer producer;

    @GetMapping("/producerMsg")
    public String getMessageFromClient(@RequestParam("message") String message) {
        try {
            producer.sendMsgtoTopic(message);
            return "Message sent: " + message;
        } catch (Exception e) {
            return "Error sending message: " + e.getMessage();
        }
    }
}
