package com.example.Kafka_user_consumer_driver.service;

import com.example.Kafka_user_consumer_driver.entity.RideRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class RideNotificationListener
{
    @KafkaListener(topics = "ride-requests", groupId = "notification-service-group")
    public void listen(RideRequest rideRequest) {
        System.out.println("Ride request received: " + rideRequest.getPassengerName() +
                " from " + rideRequest.getFromLocation() +
                " to " + rideRequest.getToLocation());
        // Logic to notify users can be added here
    }
}