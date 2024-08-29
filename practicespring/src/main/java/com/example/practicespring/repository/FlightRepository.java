package com.example.practicespring.repository;

import com.example.practicespring.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository  extends JpaRepository<Flight,Long>
{
    List<Flight> findByDepartureAirport(String departureAirport);

    List<Flight> findByArrivalAirport(String arrivalAirport);

    List<Flight> findByAirlineName(String airlineName);

}
