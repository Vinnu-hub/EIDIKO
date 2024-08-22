package com.example.AirlinesBookingApplication.service;

import com.example.AirlinesBookingApplication.entity.Booking;
//import org.hibernate.mapping.List;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface BookingService
{
Booking createBooking(Long userId, Long flightId, LocalDateTime bookingDate, String status);


    Booking updateBooking(Long id, LocalDateTime bookingDate, String status);


    void deleteBooking(Long id);

    Booking getBookingById(Long id);

    byte[] generateBookingPdf(Long id) throws IOException;


    List<Booking> getBookingHistory(Long userId);
}
