package com.example.secondspringAirlinesproject.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AirlinesDto {

    private Long airlineNumber;
    private String passengerName;
    private String sourceCountry;
    private String bookingPassNumber;
    private String aadhaarNumber;
    private Long mobileNumber; // Changed to String to match the entity
    private String destinationCountry;
    private String passportNumber;
}
