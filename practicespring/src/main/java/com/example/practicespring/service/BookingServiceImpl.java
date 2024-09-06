package com.example.practicespring.service;

import com.example.practicespring.entity.Booking;
import com.example.practicespring.entity.Flight;
//import com.example.practicespring.entity.User;
//import com.example.practicespring.entity.Usernew;
import com.example.practicespring.entity.User;
import com.example.practicespring.repository.BookingRepository;
import com.example.practicespring.repository.FlightRepository;
//import com.example.practicespring.repository.UsernewRepository;
import com.example.practicespring.exception.ResourceNotFoundException;
import com.example.practicespring.exception.PaymentIncompleteException;
import com.example.practicespring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private EmailService emailService; // Inject EmailService

    @Override
    public Booking createBooking(Long userId, Long flightId, Booking booking) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found with id: " + flightId));
        booking.setUser(user);
        booking.setFlight(flight);
        booking.setLocalDateTime(LocalDateTime.now());
        booking.setStatus("Booked");

        return bookingRepository.save(booking);
    }

    @Override
    public Booking updateBooking(Long bookingId, Booking updatedBooking) {
        Booking existingBooking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + bookingId));

        existingBooking.setStatus(updatedBooking.getStatus());
        existingBooking.setLocalDateTime(LocalDateTime.now());

        Booking savedBooking = bookingRepository.save(existingBooking);

        if ("Completed".equalsIgnoreCase(updatedBooking.getStatus())) {
            sendConfirmationEmail(existingBooking);
        }

        return savedBooking;
    }

    @Override
    public void deleteBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + bookingId));
        bookingRepository.delete(booking);
    }

    @Override
    public Booking getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + bookingId));
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public void completePayment(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + bookingId));

        if (!"Completed".equalsIgnoreCase(booking.getPaymentStatus())) {
            throw new PaymentIncompleteException("Payment is not completed. Please complete the payment first.");
        }

        booking.setPaymentStatus("Completed");
        bookingRepository.save(booking);

        sendConfirmationEmail(booking); // Send confirmation email after payment completion
    }

    private void sendConfirmationEmail(Booking booking) {
        String toEmail = booking.getUser().getEmail(); // Ensure the email address is fetched from the user
        String subject = "Booking Confirmation";
        String text = String.format("Dear %s,%n%nYour booking for flight %s has been confirmed.%nBooking ID: %d%nFlight ID: %d%nStatus: %s%n%nThank you for booking with us!",
                booking.getUser().getUserName(),
                booking.getFlight().getFlightNumber(),
                booking.getId(),
                booking.getFlight().getId(),
                booking.getStatus());

        emailService.sendBookingConfirmation(toEmail, subject, text);
    }
}
