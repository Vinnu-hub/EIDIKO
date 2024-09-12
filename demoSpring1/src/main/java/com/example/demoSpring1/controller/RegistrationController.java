package com.example.demoSpring1.controller;

import com.example.demoSpring1.entity.Registration;
import com.example.demoSpring1.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/data")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/new/user")
    public ResponseEntity<Registration> createNewUser(@RequestBody Registration registration)
    {
        Registration exist = registrationService.createNewUser(registration);
        return new ResponseEntity<>(exist, HttpStatus.CREATED); // Return the created user object in JSON
    }

    @PutMapping("/update/user/{id}")
    public ResponseEntity<Registration> updateUser(@PathVariable Long id, @RequestBody Registration registration)
    {
        Registration updatedData = registrationService.updateUser(id, registration);
        return new ResponseEntity<>(updatedData, HttpStatus.OK); // Return updated user in JSON
    }

    @GetMapping("/get/user/{id}")
    public ResponseEntity<Optional<Registration>> getUserByID(@PathVariable Long id)
    {
        Optional<Registration> result = registrationService.getUserID(id);
        return new ResponseEntity<>(result, HttpStatus.OK); // Return user data in JSON
    }

    @DeleteMapping("/delete/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id)
    {
        registrationService.deleteUser(id);
        return new ResponseEntity<>("Successfully deleted user with ID: " + id, HttpStatus.OK);
    }

    @GetMapping("/get/allusers")
    public ResponseEntity<List<Registration>> getAllUsers()
    {
        List<Registration> allUsers = (List<Registration>) registrationService.getAllUser();
        return new ResponseEntity<>(allUsers, HttpStatus.OK); // Return list of users in JSON
    }
}













