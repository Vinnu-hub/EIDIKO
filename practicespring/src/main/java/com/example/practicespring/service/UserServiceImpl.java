package com.example.practicespring.service;


import com.example.practicespring.entity.User;
//import com.example.practicespring.entity.Usernew;
import com.example.practicespring.repository.UserRepository;
//import com.example.practicespring.repository.UsernewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.example.practicespring.entity.User;


@Service
public class UserServiceImpl implements UserService
{

     @Autowired
      private UserRepository userRepository;

    @Override
    public void CreateUser(User user)
    {
        userRepository.save(user);
    }

    @Override
    public void updateUser(Long id, User user)
    {

        if (userRepository.existsById(id))
        {
            user.setId(id);
             userRepository.save(user);
        }
         else
        {
             throw new RuntimeException("User not found with id"+ id);
        }
    }




    @Override
    public void deleteUser(Long id)
    {
        if(userRepository.existsById(id))
        {
            userRepository.deleteById(id);
        }

    }


    @Override
    public Iterable<User> getAllusers()
    {
        return userRepository.findAll();
    }
}
