package com.example.demoSpring1.repository;

import com.example.demoSpring1.entity.Registration;
//import com.example.demoSpring1.entity.registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface registrationRepository  extends JpaRepository<Registration,Long> {
}
