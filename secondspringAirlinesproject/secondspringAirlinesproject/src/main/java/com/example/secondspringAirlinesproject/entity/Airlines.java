package com.example.secondspringAirlinesproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static java.util.logging.Level.ALL;

@Getter
@Setter
@NoArgsConstructor

@Entity
 @Table(name = "Airlinetable")
public class Airlines {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
   private Long airlineNumber;
    private String passengerName;
    private String sourceCountry;
    private String bookingPassNumber;
    private String AadhaarNumber;
    private Long mobileNumber;
    private String destinationCountry;
    private String passportNumber;

    @OneToMany(mappedBy = "airlineNumber", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AirlineFileHandling> files;


    public Airlines(Long airlineNumber, String passengerName, String sourceCountry, String bookingPassNumber, String aadhaarNumber, Long mobileNumber, String destinationCountry, String passportNumber) {
        this.airlineNumber = airlineNumber;
        this.passengerName = passengerName;
        this.sourceCountry = sourceCountry;
        this.bookingPassNumber = bookingPassNumber;
        AadhaarNumber = aadhaarNumber;
        this.mobileNumber = mobileNumber;
        this.destinationCountry = destinationCountry;
        this.passportNumber = passportNumber;
}
}