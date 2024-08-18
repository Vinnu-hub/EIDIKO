package com.example.secondspringAirlinesproject.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "NotComing")
public class AirlineFileHandling
{

    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

      private String filename;
      private String filetype;

      @Lob
      @Column (name="data")
      private byte[] data;

      private LocalDateTime uploadedtime;

      @ManyToOne
      @JoinColumn(name="airlineNumber", nullable = false)
      private Airlines airlineNumber;
}

