package com.example.demosecurity3.repository;

import com.example.demosecurity3.entity.Myuser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyuserRepository extends JpaRepository<Myuser,Long> {

    // in order to match the user my user name we write findByusername
    Optional<Myuser> findByUsername(String username);
}
