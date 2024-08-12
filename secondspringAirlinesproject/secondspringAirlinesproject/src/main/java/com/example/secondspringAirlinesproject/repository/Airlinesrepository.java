package com.example.secondspringAirlinesproject.repository;

import com.example.secondspringAirlinesproject.entity.Airlines;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Airlinesrepository extends JpaRepository<Airlines,Long> {
}
