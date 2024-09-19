package com.example.Kafka_user.Dto;

//
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDto {

    private String name;


    private String email;


    private String password;
}