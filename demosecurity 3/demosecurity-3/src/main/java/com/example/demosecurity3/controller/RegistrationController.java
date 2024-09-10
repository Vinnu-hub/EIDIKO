package com.example.demosecurity3.controller;


import com.example.demosecurity3.entity.Myuser;
import com.example.demosecurity3.repository.MyuserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    private MyuserRepository myuserRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/register/user")
    public Myuser createUser(@RequestBody Myuser myuser)

    {

        myuser.setPassword(passwordEncoder.encode(myuser.getPassword()));
        return  myuserRepository.save(myuser);
    }
}
