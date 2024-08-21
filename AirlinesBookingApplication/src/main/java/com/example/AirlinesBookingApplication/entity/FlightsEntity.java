package com.example.AirlinesBookingApplication.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "flights")
public class FlightsEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flight_seq")
  private Long flightId;

  @Column(name = "airline_name")
  private String airName; // Column for airline name


  private String flightNumber; // Column for flight number


  private String departureCity; // Column for departure city


  private String departurePlace; // Column for departure place


  private LocalDateTime departureDateTime; // Column for departure date and time


  private String destinationCity; // Column for destination city


  private String destinationPlace; // Column for destination place


  private Double cost; // Column for cost


  private Integer seats; // Column for seats

}
