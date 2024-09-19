package com.example.Kafka_user_consumer_driver.entity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RideRequest {
    private String passengerName;
    private String fromLocation;
    private String toLocation;
}