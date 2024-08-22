package com.example.AirlinesBookingApplication.service;

import com.example.AirlinesBookingApplication.entity.FlightsEntity;
import com.example.AirlinesBookingApplication.repository.FlightEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class FlightServiceImpl implements FlightService {

     @Autowired
    private final FlightEntityRepository flightRepository;


    @Override
    public FlightsEntity createFlight(FlightsEntity flight) {

        return flightRepository.save(flight);
    }

    @Override
    public Optional<FlightsEntity> getFlightById(Long flightId) {

        return flightRepository.findById(flightId);
    }

    @Override
    public Iterable<FlightsEntity> getAllFlights()
    {
        return flightRepository.findAll();
    }

    @Override
    public FlightsEntity updateFlight(Long flightId, FlightsEntity flight)
    {
        if (flightRepository.existsById(flightId)) {
            flight.setFlightId(flightId);
            return flightRepository.save(flight);
        }
        return null;
    }

    @Override
    public void deleteFlight(Long flightId)
    {
        flightRepository.deleteById(flightId);
    }
}
