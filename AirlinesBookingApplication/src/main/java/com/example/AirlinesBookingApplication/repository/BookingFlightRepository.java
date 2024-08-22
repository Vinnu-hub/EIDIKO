package com.example.AirlinesBookingApplication.repository;

import com.example.AirlinesBookingApplication.entity.Booking;
import com.example.AirlinesBookingApplication.entity.User;
import com.sun.source.tree.LambdaExpressionTree;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingFlightRepository extends JpaRepository<Booking,Long>
{

    List<Booking> findByUser(User user);
}
