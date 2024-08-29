package com.example.practicespring.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment_table")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String paymentStatus;


    private String paymentMethod;

    private String user_Name;

    private String transactionId;

    private Date paymentDate;


    @OneToOne
    @JoinColumn(name = "id")
    @JsonBackReference
    private Booking booking;
}
