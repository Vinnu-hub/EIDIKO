package com.example.practicespring.controller;

import com.example.practicespring.entity.Payment;
import com.example.practicespring.exception.ResourceNotFoundException;
import com.example.practicespring.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/{id}")
    public ResponseEntity<Payment> processPayment(@PathVariable("id") Long bookingId,
                                                  @RequestBody Payment paymentDetails)
    {
        try {
            Payment payment = paymentService.processPayment(bookingId, paymentDetails);
            return new ResponseEntity<>(payment, HttpStatus.CREATED);
        } catch (ResourceNotFoundException ex)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
