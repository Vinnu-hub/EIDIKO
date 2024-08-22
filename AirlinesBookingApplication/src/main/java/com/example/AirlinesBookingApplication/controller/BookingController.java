package com.example.AirlinesBookingApplication.controller;

import com.example.AirlinesBookingApplication.Dto.BookingRequest;
import com.example.AirlinesBookingApplication.Dto.BookingUpdateRequest;
import com.example.AirlinesBookingApplication.entity.Booking;
import com.example.AirlinesBookingApplication.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/journey-ticket")
    public ResponseEntity<Booking> createBooking(@RequestBody BookingRequest bookingRequest) {
        try {
            Booking booking = bookingService.createBooking(
                    bookingRequest.getUserId(),
                    bookingRequest.getFlightId(),
                    bookingRequest.getBookingDate(),
                    bookingRequest.getStatus()
            );
            return new ResponseEntity<>(booking, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log the exception (e.g., logger.error("Error creating booking", e);)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(
            @PathVariable Long id,
            @RequestBody BookingUpdateRequest updateRequest) {
        try {
            Booking booking = bookingService.updateBooking(
                    id,
                    updateRequest.getBookingDate(),
                    updateRequest.getStatus()
            );
            return new ResponseEntity<>(booking, HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception (e.g., logger.error("Error updating booking", e);)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        try {
            bookingService.deleteBooking(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            // Log the exception (e.g., logger.error("Error deleting booking", e);)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> getBookingPdf(@PathVariable Long id) {
        try {
            byte[] pdfContents = bookingService.generateBookingPdf(id);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/pdf");
            headers.add("Content-Disposition", "attachment; filename=booking_" + id + ".pdf");

            return new ResponseEntity<>(pdfContents, headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/users/booking-history/{id}")
    public ResponseEntity<List<Booking>> getBookingHistory(@PathVariable Long userId) {
        try {
            List<Booking> bookings = bookingService.getBookingHistory(userId);
            return new ResponseEntity<>(bookings, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}























