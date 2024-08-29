package com.example.practicespring.exception;



public class PaymentIncompleteException extends RuntimeException {
    public PaymentIncompleteException(String message) {
        super(message);
    }
}
