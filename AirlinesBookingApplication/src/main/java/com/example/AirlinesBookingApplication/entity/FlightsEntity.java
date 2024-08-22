package com.example.AirlinesBookingApplication.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
  @SequenceGenerator(name = "flight_seq", sequenceName = "flight_sequence", allocationSize = 1)
  private Long flightId;

  @Column(name = "airline_name", nullable = false)
  private String airName; // Column for airline name

  @Column(name = "flight_number", nullable = false, unique = true)
  private String flightNumber; // Column for flight number  IndiGo
//  Air India
//  SpiceJet
//  GoAir (now GoFirst)
//  Vistara

  @Column(name = "departure_city", nullable = false)
  private String departureCity; // Column for departure city

  @Column(name = "departure_place", nullable = false)
  private String departurePlace; // Column for departure place

  @Column(name = "departure_date_time", nullable = false)
  private LocalDateTime departureDateTime; // Column for departure date and time

  @Column(name = "destination_city", nullable = false)
  private String destinationCity; // Column for destination city

  @Column(name = "destination_place", nullable = false)
  private String destinationPlace; // Column for destination place

  @Column(name = "cost", nullable = false)
  private Double cost; // Column for cost

  @Column(name = "seats", nullable = false)
  private Integer seats; // Column for seats

  @OneToMany(mappedBy = "flight")
  @JsonBackReference
  private Set<Booking> bookings; // Assuming there is a Booking entity with a flight reference

}
