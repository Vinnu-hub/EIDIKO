package com.example.practicespring.repository;

import com.example.practicespring.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
