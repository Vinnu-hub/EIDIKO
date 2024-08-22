package com.example.AirlinesBookingApplication.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "users")
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "full_name", nullable = false)
   private String fullName;

   @Column(name = "email", nullable = false, unique = true)
   private String email;

   @Column(name = "mobile_number", nullable = false, unique = true)
   private String mobileNumber;

   @Column(name = "aadhaar_number", nullable = false, unique = true)
   private String aadhaarNumber;

   @Column(name = "pan_number")
   private String panNumber;

   @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   private Set<Booking> bookings;



   @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   private List<Documents> documents;
}
