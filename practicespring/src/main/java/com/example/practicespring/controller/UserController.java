package com.example.practicespring.controller;

//import com.example.practicespring.entity.User;
import com.example.practicespring.entity.User;
//import com.example.practicespring.entity.Usernew;
import com.example.practicespring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;


@RestController
@RequestMapping("/api/map")
public class UserController
{
    @Autowired
    private  UserService userService;


     @PostMapping("/saves")
    public ResponseEntity<User> CreateUser(@RequestBody User user)
     {
         userService.CreateUser(user);
          return ResponseEntity.ok(user);
     }

     @PutMapping("/update")

     public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user)
     {
         userService.updateUser(id,user);
          return ResponseEntity.ok(user);
     }


     @DeleteMapping("/delete")

     public ResponseEntity<User>  deleteUser( @PathVariable Long id)
     {
         userService.deleteUser(id);
          return ResponseEntity.noContent().build();
     }

     @GetMapping("/getall")
     public ResponseEntity<Iterable<User>>  getAllusers()
     {
         Iterable<User> user =userService.getAllusers();
          return ResponseEntity.ok(user);
     }

}


//JSON (JavaScript Object Notation) is a lightweight data interchange format that's easy for humans
//to read and write and easy for machines to parse and generate.
//        It's commonly used for transmitting data in web applications between a server and a client.