package com.example.practicespring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class RegisterDocuments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private String fileExtension;

    @Lob
    private String data;

    private LocalDateTime uploadedTime;

    private String documentType;

    @ManyToOne
    @JoinColumn(name = "registration_id", nullable = false)
    private Registration registration;
}
