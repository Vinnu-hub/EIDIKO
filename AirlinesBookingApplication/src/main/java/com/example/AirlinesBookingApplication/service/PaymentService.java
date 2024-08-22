package com.example.AirlinesBookingApplication.service;

public interface PaymentService {
    boolean processPayment(Long userId, Double amount);
}
