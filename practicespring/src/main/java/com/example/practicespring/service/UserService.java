package com.example.practicespring.service;

import com.example.practicespring.entity.User;
//mport com.example.practicespring.entity.Usernew;

public interface UserService
{

     void  CreateUser(User user);

      void  updateUser(Long id, User user);

       void deleteUser(Long id);

//         void deleteUserById(Long id);

         Iterable<User> getAllusers();


}
