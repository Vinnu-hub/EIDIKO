package com.example.practicespring.service;

import com.example.practicespring.entity.Booking;
import com.example.practicespring.entity.Payment;
import com.example.practicespring.repository.BookingRepository;
import com.example.practicespring.repository.PaymentRepository;
import com.example.practicespring.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Transactional
    public Payment processPayment(Long Id, Payment paymentDetails)
    {
        // Retrieve the booking
        Booking booking = bookingRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + Id));

        // Set payment details
        paymentDetails.setBooking(booking);
        paymentDetails.setPaymentDate(new Date());
        paymentDetails.setTransactionId(UUID.randomUUID().toString());
        paymentDetails.setPaymentStatus("Completed"); // Set status to Completed

        // Save payment
        Payment savedPayment = paymentRepository.save(paymentDetails);

        // Update booking status
        booking.setPaymentStatus("Completed");
        bookingRepository.save(booking);

        return savedPayment;
    }
}
