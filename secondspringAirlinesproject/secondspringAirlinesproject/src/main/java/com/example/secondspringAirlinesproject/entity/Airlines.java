package com.example.secondspringAirlinesproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Airlines {

    @Id
    private Long airlineNumber;
    private String passengerName;
    private String sourceCountry;
    private String bookingPassNumber;
    private String AadhaarNumber;
    private Long mobileNumber;
    private String destinationCountry;
    private String passportNumber;
}
