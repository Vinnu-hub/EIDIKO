package com.example.AirlinesBookingApplication.repository;

import com.example.AirlinesBookingApplication.entity.FlightsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightEntityRepository extends JpaRepository<FlightsEntity,Long> {
}
