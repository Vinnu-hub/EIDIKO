package com.example.practicespring.service;

import com.example.practicespring.entity.Flight;

import java.util.List;
import java.util.Optional;

public interface FlightService


{
    Flight saveFlight(Flight flight);

    Optional<Flight> getFlightById(Long id);

    void deleteFlight(Long id);

    List<Flight> getAllFlights();

    List<Flight> getFlightsByDepartureAirport(String departureAirport);

    List<Flight> getFlightsByArrivalAirport(String arrivalAirport);


    List<Flight> getFlightsByAirlineName(String airlineName);


}
