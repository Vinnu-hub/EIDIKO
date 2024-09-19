package com.example.kafka_cab_booking_System.entity;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RideRequest
{
    private String passengerName;
    private String pickupLocation;
    private String dropoffLocation;
}
