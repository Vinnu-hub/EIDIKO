package com.example.FirstSpringProject.repository;

import com.example.FirstSpringProject.entity.product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface productRepository extends JpaRepository<product, Integer>{
}
