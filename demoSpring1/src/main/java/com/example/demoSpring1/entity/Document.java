package com.example.demoSpring1.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter


    @Entity
    public class Document
{

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO) // or GenerationType.AUTO
        private Long id;

        private String fileName;

        private String documentType;

        private LocalDateTime localDateTime;

        @Lob
        private  String data;

        @ManyToOne
        @JoinColumn(name = "registration_id") // Foreign key column for the relationship
        private Registration registration;
    }

