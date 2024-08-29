package com.example.practicespring.service;

import com.example.practicespring.entity.Registration;

public interface RegistrationService
{

    Registration createnewUser(Registration registration);

     Registration UsergetById(Long id);

     Registration updateUser(Long id,Registration registration);

      void deleteUSer(Long id);
}

// without void -->  we can write the return
// with void we cannot write the return type
