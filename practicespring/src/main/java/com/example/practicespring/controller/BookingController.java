package com.example.practicespring.controller;

import com.example.practicespring.entity.Booking;
import com.example.practicespring.service.BookingService;
import com.example.practicespring.exception.ResourceNotFoundException;
import com.example.practicespring.exception.PaymentIncompleteException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/save")
    public ResponseEntity<Booking> createBooking(
            @RequestParam Long userId,
            @RequestParam Long flightId,
            @RequestBody Booking booking) {
        try {
            Booking createdBooking = bookingService.createBooking(userId, flightId, booking);
            return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{bookingId}")
    public ResponseEntity<Booking> updateBooking(
            @PathVariable Long bookingId,
            @RequestBody Booking updatedBooking) {
        try {
            Booking booking = bookingService.updateBooking(bookingId, updatedBooking);
            return new ResponseEntity<>(booking, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<HttpStatus> deleteBooking(@PathVariable Long bookingId) {
        try {
            bookingService.deleteBooking(bookingId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long bookingId) {
        try {
            Booking booking = bookingService.getBookingById(bookingId);
            return new ResponseEntity<>(booking, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @PostMapping("/{bookingId}/complete-payment")
    public ResponseEntity<HttpStatus> completePayment(@PathVariable Long bookingId) {
        try {
            bookingService.completePayment(bookingId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (PaymentIncompleteException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
