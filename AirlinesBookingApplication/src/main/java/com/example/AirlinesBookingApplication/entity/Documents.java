package com.example.AirlinesBookingApplication.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "documents")
public class Documents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "filename")
    private String filename;

    @Column(name = "filetype")
    private String filetype;

    @Lob
    @Column(name = "data")
    private byte[] data;

    @Column(name = "uploaded_time")
    private LocalDateTime uploadedTime;

    @Column(name = "document_type") // New field for document type
    private String documentType;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;
}
