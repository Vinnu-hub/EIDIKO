package com.example.secondspringAirlinesproject.repository;

import com.example.secondspringAirlinesproject.entity.AirlineFileHandling;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileHandlingRepository extends JpaRepository<AirlineFileHandling, Long> {
}
