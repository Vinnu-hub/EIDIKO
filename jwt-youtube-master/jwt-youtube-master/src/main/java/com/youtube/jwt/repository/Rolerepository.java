package com.youtube.jwt.repository;

import com.youtube.jwt.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Rolerepository extends CrudRepository<Role, String> {

}
