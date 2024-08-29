package com.example.practicespring.service;

import com.example.practicespring.entity.Flight;
import com.example.practicespring.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService
{

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public Flight saveFlight(Flight flight)
    {
        return flightRepository.save(flight);
    }

    @Override
    public Optional<Flight> getFlightById(Long id)
    {
        return flightRepository.findById(id);
    }

    @Override
    public void deleteFlight(Long id)
    {
        flightRepository.deleteById(id);
    }

    @Override
    public List<Flight> getAllFlights()
    {
        return flightRepository.findAll();
    }

    @Override
    public List<Flight> getFlightsByDepartureAirport(String departureAirport)
    {
        return flightRepository.findByDepartureAirport(departureAirport);
    }

    @Override
    public List<Flight> getFlightsByArrivalAirport(String arrivalAirport)
    {
        return flightRepository.findByArrivalAirport(arrivalAirport);
    }

    @Override
    public List<Flight> getFlightsByAirlineName(String airlineName)
    {
        return flightRepository.findByAirlineName(airlineName);
    }
}
