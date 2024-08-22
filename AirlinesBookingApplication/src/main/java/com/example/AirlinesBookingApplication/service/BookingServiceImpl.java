package com.example.AirlinesBookingApplication.service;

import com.example.AirlinesBookingApplication.entity.Booking;
import com.example.AirlinesBookingApplication.entity.FlightsEntity;
import com.example.AirlinesBookingApplication.entity.User;
import com.example.AirlinesBookingApplication.repository.BookingFlightRepository;
import com.example.AirlinesBookingApplication.repository.FlightEntityRepository;
import com.example.AirlinesBookingApplication.repository.UserRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingFlightRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FlightEntityRepository flightRepository;

    @Autowired
    private PaymentService paymentService;

//    @Autowired
//    private EmailService emailService;

    @Override
    public Booking createBooking(Long userId, Long flightId, LocalDateTime bookingDate, String status) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        FlightsEntity flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        // Assume the cost is fetched from the flight details
        Double amount = flight.getCost();

        // Process payment
        boolean paymentSuccessful = paymentService.processPayment(userId, amount);

        if (!paymentSuccessful) {
            throw new RuntimeException("Payment failed");
        }

        // Payment was successful; proceed with booking
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setFlight(flight);
        booking.setBookingDate(bookingDate);
        booking.setStatus(status);

        Booking savedBooking = bookingRepository.save(booking);
//
//        // Send email to user after booking is created
//        String subject = "Booking Confirmation";
//        String text = String.format("Dear %s,\n\nYour booking with flight number %s has been confirmed." +
//                        "\n\nBooking Date: %s\nFlight Details:\n- Departure: %s\n- " +
//                        "Destination: %s\n\nThank you for booking with us.",
//                user.getFullName(), flight.getFlightNumber(),
//                bookingDate, flight.getDepartureCity(), flight.getDestinationCity());
//
//        emailService.sendBookingConfirmation(user.getEmail(), subject, text);

        return savedBooking;
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

    @Override
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    @Override
    public byte[] generateBookingPdf(Long id) throws IOException {
        Booking booking = getBookingById(id);

        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, outputStream);
            document.open();

            document.add(new Paragraph("Booking Confirmation"));
            document.add(new Paragraph("Booking ID: " + booking.getId()));
            document.add(new Paragraph("User: " + booking.getUser().getFullName()));
            document.add(new Paragraph("Flight ID: " + booking.getFlight().getFlightId()));
            document.add(new Paragraph("Flight Number: " + booking.getFlight().getFlightNumber()));
            document.add(new Paragraph("Booking Date: " + booking.getBookingDate().toString()));
            document.add(new Paragraph("Status: " + booking.getStatus()));

            document.close();
        } catch (DocumentException e) {
            throw new IOException("Error creating PDF document", e);
        }

        return outputStream.toByteArray();
    }

    @Override
    public List<Booking> getBookingHistory(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return bookingRepository.findByUser(user);
    }
    }

