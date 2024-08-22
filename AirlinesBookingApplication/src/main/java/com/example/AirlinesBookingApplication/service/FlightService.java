package com.example.AirlinesBookingApplication.service;

import com.example.AirlinesBookingApplication.entity.FlightsEntity;

import java.util.Optional;

public interface FlightService {

    FlightsEntity createFlight(FlightsEntity flight);

    Optional<FlightsEntity> getFlightById(Long flightId);

    Iterable<FlightsEntity> getAllFlights();

    FlightsEntity updateFlight(Long flightId, FlightsEntity flight);

    void deleteFlight(Long flightId);
}