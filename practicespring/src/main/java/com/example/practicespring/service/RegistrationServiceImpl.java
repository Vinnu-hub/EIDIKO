package com.example.practicespring.service;


import com.example.practicespring.entity.Registration;
import com.example.practicespring.repository.RegistrationRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService
{

    @Autowired
     private RegistrationRepository registrationRepository;

     @Override
    public Registration createnewUser(Registration registration)
     {
          return registrationRepository.save(registration);
     }

      @Override
    public   Registration updateUser(Long id,Registration registration)
     {
         Registration existing=registrationRepository.findById(id).orElseThrow (() -> new RuntimeException("user not found"));
          if(registration.getFullName()!=null)
          {
              existing.setFullName(registration.getFullName());
          }
           if(registration.getEmail()!=null)
           {
               existing.setEmail(registration.getEmail());
           }
           if(registration.getMobileNumber()!=null)
                existing.setMobileNumber(registration.getMobileNumber());
            if(registration.getStatus()!=null)
                 existing.setStatus(registration.getStatus());

          return    registrationRepository.save(existing);
     }

    @Override
    public void deleteUSer(Long id) {
        registrationRepository.deleteById(id);
    }

      @Override
public Registration UsergetById(Long id)
      {
           return registrationRepository.findById(id) . orElseThrow(() -> new RuntimeException("user id is not found"));

      }
}
