package com.example.AirlinesBookingApplication.controller;

import com.example.AirlinesBookingApplication.entity.Booking;
import com.example.AirlinesBookingApplication.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/journey-ticket")
    public ResponseEntity<Booking> createBooking(@RequestParam Long userId,
                                                 @RequestParam Long flightId,
                                                 @RequestParam LocalDateTime bookingDate,
                                                 @RequestParam String status) {
        try {
            Booking booking = bookingService.createBooking(userId, flightId, bookingDate, status);
            return new ResponseEntity<>(booking, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Long id,
                                                 @RequestParam LocalDateTime bookingDate,
                                                 @RequestParam String status) {
        try {
            Booking booking = bookingService.updateBooking(id, bookingDate, status);
            return new ResponseEntity<>(booking, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        try {
            bookingService.deleteBooking(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
