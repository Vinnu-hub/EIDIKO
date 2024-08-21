package com.example.AirlinesBookingApplication.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "bookings")
public class Booking {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "booking_date")
   private LocalDateTime bookingDate;

   @Column(nullable = false)
   private String status;

   @ManyToOne
   @JoinColumn(name = "flight_id", nullable = false)
   private FlightsEntity flight;

   @ManyToOne
   @JoinColumn(name = "user_id", nullable = false)
   private User user;
}
