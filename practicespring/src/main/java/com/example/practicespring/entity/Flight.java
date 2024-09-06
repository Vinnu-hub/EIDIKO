package com.example.practicespring.entity;

//import jakarta.persistence.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//
//import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String airlineName;

    private String arrivalAirport;

    private LocalDateTime arrivalTime;

    private String departureAirport;

    private String departureCity;

    private LocalDateTime departureTime;

    private String destinationCity;

    private String flightClass;

    private String flightName;

    private String flightNumber;

    private Double price;

    private Integer totalSeats;


}
