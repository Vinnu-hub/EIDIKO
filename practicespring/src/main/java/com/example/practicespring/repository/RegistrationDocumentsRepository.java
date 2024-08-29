package com.example.practicespring.repository;

import com.example.practicespring.entity.RegisterDocuments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationDocumentsRepository extends JpaRepository<RegisterDocuments,Long> {
}
