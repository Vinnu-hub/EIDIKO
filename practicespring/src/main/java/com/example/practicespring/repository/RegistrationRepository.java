package com.example.practicespring.repository;

import com.example.practicespring.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository <Registration,Long>{
}
