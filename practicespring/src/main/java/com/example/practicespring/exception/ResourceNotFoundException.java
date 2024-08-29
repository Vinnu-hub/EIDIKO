package com.example.practicespring.exception;

public class ResourceNotFoundException extends RuntimeException
{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
