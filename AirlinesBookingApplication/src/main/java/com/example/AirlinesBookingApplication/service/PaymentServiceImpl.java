package com.example.AirlinesBookingApplication.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public boolean processPayment(Long userId, Double amount)
    {
        if (amount > 0) {
            return true;
        }
        return false;
    }
}
