package com.example.practicespring.controller;


import com.example.practicespring.entity.Registration;
import com.example.practicespring.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-registration")
public class RegistrstionController
{

     @Autowired
     private RegistrationService registrationService;

     @PostMapping("/save")
    public ResponseEntity<String>  createnewUser(  @RequestBody  Registration registration)
     {
        registrationService.createnewUser(registration);
         return new ResponseEntity<>("succefully registration is completed", HttpStatus.OK);
     }


      @GetMapping("/value")
    public ResponseEntity<String> UsergetById( @PathVariable Long id)
      {
  Registration registration=registrationService.UsergetById(id);
  return new ResponseEntity<String>(String.valueOf(registration),HttpStatus.OK);
      }


      @PutMapping("/savedata")

    public ResponseEntity<String> updateUser(Long id, Registration registration)
      {
        registrationService.updateUser(id,registration);
        return new ResponseEntity<>("updated sucessfully completed", HttpStatus.OK);
      }

       @GetMapping("/deleteUser")
      public  ResponseEntity<String>  deleteUSer(Long id)
{
     registrationService.deleteUSer(id);
     return new ResponseEntity<>("your data is", HttpStatus.OK);

}



}
