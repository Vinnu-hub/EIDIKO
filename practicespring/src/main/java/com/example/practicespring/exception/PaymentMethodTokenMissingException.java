package com.example.practicespring.exception;

public class PaymentMethodTokenMissingException extends RuntimeException {
    public PaymentMethodTokenMissingException(String message) {
        super(message);
    }
}
