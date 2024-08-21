package com.example.AirlinesBookingApplication.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookingRequestDto {
    private Long userId;
    private Long flightId;
    private LocalDateTime bookingDate;
    private String status;
}
