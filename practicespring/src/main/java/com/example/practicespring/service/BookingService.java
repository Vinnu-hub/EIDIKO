package com.example.practicespring.service;

import com.example.practicespring.entity.Booking;

import java.util.List;

public interface BookingService
{


    Booking createBooking(Long Id, Long flightId, Booking booking);


    Booking updateBooking(Long bookingId, Booking updatedBooking);


    void deleteBooking(Long bookingId);


    Booking getBookingById(Long bookingId);


    List<Booking> getAllBookings();


    void completePayment(Long bookingId);
}
