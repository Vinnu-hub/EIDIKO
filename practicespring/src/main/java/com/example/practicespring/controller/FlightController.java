package com.example.practicespring.controller;

import com.example.practicespring.entity.Flight;
import com.example.practicespring.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightController
{

    @Autowired
    private FlightService flightService;

    @PostMapping("/select")
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight)
    {
        Flight savedFlight = flightService.saveFlight(flight);
        return new ResponseEntity<>(savedFlight, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id)
    {
        Optional<Flight> flight = flightService.getFlightById(id);
        return flight.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id)
    {
        flightService.deleteFlight(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/allflights")
    public ResponseEntity<List<Flight>> getAllFlights()
    {
        List<Flight> flights = flightService.getAllFlights();
        return ResponseEntity.ok(flights);
    }

    @GetMapping("/departureAirport")
    public ResponseEntity<List<Flight>> getFlightsByDepartureAirport(@RequestParam String departureAirport)
    {
        List<Flight> flights = flightService.getFlightsByDepartureAirport(departureAirport);
        return ResponseEntity.ok(flights);
    }

    @GetMapping("/arrivalAirport")
    public ResponseEntity<List<Flight>> getFlightsByArrivalAirport(@RequestParam String arrivalAirport)
    {
        List<Flight> flights = flightService.getFlightsByArrivalAirport(arrivalAirport);
        return ResponseEntity.ok(flights);
    }

    @GetMapping("/airlineName")
    public ResponseEntity<List<Flight>> getFlightsByAirlineName(@RequestParam String airlineName)
    {
        List<Flight> flights = flightService.getFlightsByAirlineName(airlineName);
        return ResponseEntity.ok(flights);
    }
}
