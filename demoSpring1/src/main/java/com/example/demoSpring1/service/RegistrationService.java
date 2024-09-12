package com.example.demoSpring1.service;

import com.example.demoSpring1.entity.Registration;

import java.util.List;
import java.util.Optional;
//import com.example.demoSpring1.entity.registration;

public interface RegistrationService
{

    Registration createNewUser(Registration registration);


    Optional<Registration> getUserID(Long id);

    Registration updateUser(Long id, Registration registration);

    void deleteUser(Long id);

    List<Registration> getAllUser();
}
