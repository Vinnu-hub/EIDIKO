package com.example.AirlinesBookingApplication.repository;

import com.example.AirlinesBookingApplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
