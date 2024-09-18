package com.example.springkafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final String TOPIC = "kafka_basic_program"; // Use a valid topic name

    public void sendMsgtoTopic(String message) {
        kafkaTemplate.send(TOPIC, message);
    }
}
