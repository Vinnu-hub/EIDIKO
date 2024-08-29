package com.example.practicespring.Dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest {
    private Long userId;
    private Long flightId;
    private String bookingDate; // Use ISO date format or specify format
    private String status;

    // Getters and Setters
}
