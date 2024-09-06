package com.example.practicespring.repository;

//import com.example.practicespring.entity.User;
//import com.example.practicespring.entity.Usernew;
import com.example.practicespring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {
}
