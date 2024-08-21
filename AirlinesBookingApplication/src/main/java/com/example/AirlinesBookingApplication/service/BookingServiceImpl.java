package com.example.AirlinesBookingApplication.service;

import com.example.AirlinesBookingApplication.entity.Booking;
import com.example.AirlinesBookingApplication.entity.FlightsEntity;
import com.example.AirlinesBookingApplication.entity.FlightsEntity;
import com.example.AirlinesBookingApplication.entity.User;
import com.example.AirlinesBookingApplication.repository.BookingFlightRepository;
import com.example.AirlinesBookingApplication.repository.BookingFlightRepository;
import com.example.AirlinesBookingApplication.repository.FlightEntityRepository;
import com.example.AirlinesBookingApplication.repository.FlightEntityRepository;
import com.example.AirlinesBookingApplication.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class BookingServiceImpl implements BookingService {

    private final BookingFlightRepository bookingRepository;
    private final UserRepository userRepository;
    private final FlightEntityRepository flightRepository;

    @Override
    public Booking createBooking(Long userId, Long flightId, LocalDateTime bookingDate, String status) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        FlightsEntity flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setFlight(flight);
        booking.setBookingDate(bookingDate);
        booking.setStatus(status);

        return bookingRepository.save(booking);
    }

    @Override
    public Booking updateBooking(Long id, LocalDateTime bookingDate, String status) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setBookingDate(bookingDate);
        booking.setStatus(status);

        return bookingRepository.save(booking);
    }

    @Override
    public void deleteBooking(Long id) {
        if (!bookingRepository.existsById(id)) {
            throw new RuntimeException("Booking not found");
        }
        bookingRepository.deleteById(id);
    }
}
