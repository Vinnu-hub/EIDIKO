package com.example.AirlinesBookingApplication.repository;

import com.example.AirlinesBookingApplication.entity.Documents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentsRepsoitory extends JpaRepository<Documents,Long> {
}
