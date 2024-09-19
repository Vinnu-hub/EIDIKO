package com.example.kafka_cab_booking_System.service;

import com.example.kafka_cab_booking_System.entity.RideRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class RideRequestProducer
{
    private final KafkaTemplate<String, RideRequest> kafkaTemplate;

    public RideRequestProducer(KafkaTemplate<String, RideRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendRideRequest(String topics, RideRequest rideRequest) {
        kafkaTemplate.send(topics, rideRequest);
    }
}

