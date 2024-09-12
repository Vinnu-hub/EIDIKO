package com.example.demoSpring1.service;

import com.example.demoSpring1.entity.Registration;
import com.example.demoSpring1.exception.UseridNotFound;
import com.example.demoSpring1.repository.registrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class RegistrationServiceImpl implements RegistrationService
{
    @Autowired
    private registrationRepository registrationRepo;


    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Registration createNewUser(Registration registration)
    {
        registration.setPassword(passwordEncoder.encode(registration.getPassword()));
       return    registrationRepo.save(registration);
    }

    @Override
    public Optional<Registration> getUserID(Long id)
    {
        return registrationRepo.findById(id);
    }

    @Override
    public Registration updateUser(Long id, Registration registration)
    {

        Registration existing =registrationRepo.findById(id).orElseThrow(() -> new UseridNotFound("user id not present "));


        if(registration.getFullname()!=null)
            existing.setFullname(registration.getFullname());
        if (registration.getEmail()!=null)
            existing.setEmail(registration.getEmail());

         if(registration.getContact()!=null)
             existing.setContact(registration.getContact());
         if(registration.getAadress()!=null)
             existing.setAadress(registration.getAadress());

         if(registration.getPassword()!=null)
         {
             existing.setPassword(passwordEncoder.encode(registration.getPassword()));
         }
          return  registrationRepo.save(existing);

    }

    @Override
    public void deleteUser(Long id) {
         registrationRepo.deleteById(id);

    }

    @Override
    public List<Registration> getAllUser()
    {
        return registrationRepo.findAll(); // No need for angle brackets
    }

}
