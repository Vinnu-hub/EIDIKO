package com.example.FileHandlingSpringproject.repository;

import com.example.FileHandlingSpringproject.entity.FileOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface fileRepository extends JpaRepository<FileOperation, Integer> {

}
