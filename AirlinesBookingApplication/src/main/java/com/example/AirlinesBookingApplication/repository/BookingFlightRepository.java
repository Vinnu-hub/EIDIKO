package com.example.AirlinesBookingApplication.repository;

import com.example.AirlinesBookingApplication.entity.Booking;
import com.example.AirlinesBookingApplication.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingFlightRepository extends JpaRepository<Booking,Long> {
}
