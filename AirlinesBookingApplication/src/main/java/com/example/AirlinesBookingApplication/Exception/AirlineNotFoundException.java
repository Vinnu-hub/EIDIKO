package com.example.AirlinesBookingApplication.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AirlineNotFoundException extends RuntimeException {

    public AirlineNotFoundException(String message)
    {

        super(message);
    }
}
