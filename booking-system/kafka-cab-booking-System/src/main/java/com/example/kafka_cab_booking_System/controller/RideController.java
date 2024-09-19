package com.example.kafka_cab_booking_System.controller;

import com.example.kafka_cab_booking_System.entity.RideRequest;
import com.example.kafka_cab_booking_System.service.RideRequestProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ride")
public class RideController {

    private final RideRequestProducer rideRequestProducer;

    public RideController(RideRequestProducer rideRequestProducer) {
        this.rideRequestProducer = rideRequestProducer;
    }

    @PostMapping("/request")
    public ResponseEntity<String> requestRide(@RequestBody RideRequest rideRequest) {
        rideRequestProducer.sendRideRequest("ride-requests", rideRequest);
        return ResponseEntity.ok("Ride request sent successfully for " + rideRequest.getPassengerName());
    }
}