package com.example.AirlinesBookingApplication.service;

import com.example.AirlinesBookingApplication.entity.Booking;

import java.time.LocalDateTime;

public interface BookingService {

    Booking createBooking(Long userId, Long flightId, LocalDateTime bookingDate, String status);

    Booking updateBooking(Long id, LocalDateTime bookingDate, String status);

    void deleteBooking(Long id);
}
