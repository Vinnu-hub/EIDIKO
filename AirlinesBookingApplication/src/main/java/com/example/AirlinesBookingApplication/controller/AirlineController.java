package com.example.AirlinesBookingApplication.controller;

import com.example.AirlinesBookingApplication.entity.User;
import com.example.AirlinesBookingApplication.service.AirlineService;
import com.example.AirlinesBookingApplication.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AirlineController {

    @Autowired
    private final AirlineService airlineService;



    @PostMapping("/users")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        airlineService.registerUser(user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User user) {
        airlineService.updateUser(id, user);
        return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        airlineService.deleteUser(id);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = airlineService.getUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<Iterable<User>> getAllUsers() {
        Iterable<User> users = airlineService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

//    @PostMapping("/flights/{flightId}/users/{userId}/book")
//    public ResponseEntity<String> bookFlight(@PathVariable Long flightId, @PathVariable Long userId) {
//        try {
//            airlineService.bookFlight(flightId, userId);
//            return new ResponseEntity<>("Flight booked successfully", HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>("Error booking flight: " + e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @PutMapping("/bookings/{id}")
//    public ResponseEntity<String> updateBooking(@PathVariable Long id, @RequestBody Booking booking) {
//        airlineService.updateBooking(id, booking);
//        return new ResponseEntity<>("Booking updated successfully", HttpStatus.OK);
//    }
}
    //================================================================================================


