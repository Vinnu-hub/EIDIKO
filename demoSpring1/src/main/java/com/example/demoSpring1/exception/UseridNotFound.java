package com.example.demoSpring1.exception;

public class UseridNotFound extends RuntimeException{
    public UseridNotFound(String message)
    {
        super(message);
    }
}
