package com.youtube.jwt.repository;

import com.youtube.jwt.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Userrepository extends CrudRepository<User, String> {
}
