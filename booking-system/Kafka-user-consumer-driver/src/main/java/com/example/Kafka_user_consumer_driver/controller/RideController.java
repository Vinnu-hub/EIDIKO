package com.example.Kafka_user_consumer_driver.controller;

import com.example.Kafka_user_consumer_driver.entity.RideRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rides")
public class RideController
{

    @PostMapping("/accept")
    public ResponseEntity<String> acceptRide(@RequestBody RideRequest rideRequest) {
        System.out.println("Received request: " + rideRequest);

        System.out.println("Driver accepted ride request for: " + rideRequest.getPassengerName() +
                " from " + rideRequest.getFromLocation() + " to " + rideRequest.getToLocation());

        return ResponseEntity.ok("Driver accepted ride request for " + rideRequest.getPassengerName() +
                " from " + rideRequest.getFromLocation() + " to " + rideRequest.getToLocation());
    }



    @PostMapping("/reject")
    public String rejectRideRequest(@RequestBody RideRequest rideRequest) {
        // Logic to reject the ride request
        System.out.println("Driver rejected ride request for: " + rideRequest.getPassengerName());
        // Notify the passenger or handle further logic here
        return "Ride request rejected for: " + rideRequest.getPassengerName();
    }




}