package com.example.secondspringAirlinesproject.repository;

import com.example.secondspringAirlinesproject.entity.Airlines;
import org.springframework.data.jpa.repository.JpaRepository;
//In Spring Boot, the repository pattern is used to abstract and encapsulate ,
// data access operations, making it easier to manage data persistence in your application.
//The repository interface extends JpaRepository (or another Spring Data interface), which provides built-in methods for common CRUD (Create, Read, Update, Delete) operations.
// These methods include saving entities, finding entities by their primary key, deleting entities, and more.
public interface Airlinesrepository extends JpaRepository<Airlines,Long> {
}
