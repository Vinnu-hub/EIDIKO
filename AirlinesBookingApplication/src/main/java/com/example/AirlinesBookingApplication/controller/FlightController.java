package com.example.AirlinesBookingApplication.controller;

import com.example.AirlinesBookingApplication.entity.FlightsEntity;
import com.example.AirlinesBookingApplication.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping("/chose")
    public ResponseEntity<FlightsEntity> createFlight(@RequestBody FlightsEntity flight) {
        FlightsEntity createdFlight = flightService.createFlight(flight);
        return new ResponseEntity<>(createdFlight, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightsEntity> getFlightById(@PathVariable Long id) {
        Optional<FlightsEntity> flight = flightService.getFlightById(id);
        return flight.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/Allflights")
    public ResponseEntity<Iterable<FlightsEntity>> getAllFlights() {
        Iterable<FlightsEntity> flights = flightService.getAllFlights();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FlightsEntity> updateFlight(@PathVariable Long id, @RequestBody FlightsEntity flight) {
        FlightsEntity updatedFlight = flightService.updateFlight(id, flight);
        return updatedFlight != null ? new ResponseEntity<>(updatedFlight, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return ResponseEntity.noContent().build();
    }
}
