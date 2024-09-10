package com.example.demosecurity3.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {


    @GetMapping("/home")
     public String handleWelcome()
    {
         return  "home";
    }

     @GetMapping("/user/home")
     public String handleuserWelcome()
     {
         return "user -home";
     }



     @GetMapping("/admin/home")
    public String adminhomeWelcome()
     {
          return  " admin-home";
     }
}
