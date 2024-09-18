package com.example.springkafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @KafkaListener(topics = "kafka_basic_program", groupId = "kafka_group") // Corrected group ID format
    public void listenToTopic(String receivedMessage) {
        System.out.println("The message is received: " + receivedMessage); // Added space for readability
    }
}
