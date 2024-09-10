package com.example.demosecurity3.entity;

import com.example.demosecurity3.repository.MyuserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service // Ensure this is registered as a Spring service
public class MyUserService implements UserDetailsService {

    @Autowired
    private MyuserRepository myuserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Myuser> myuser = myuserRepository.findByUsername(username);

        if (myuser.isPresent()) {
            Myuser userObj = myuser.get();

            return User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .roles(getRoles(userObj))
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    // Helper method to fetch user roles and return them as a String array
    public String[] getRoles(Myuser user) {
        if (user.getRole() == null || user.getRole().isEmpty()) {
            // Default role if no role is set
            return new String[]{"USER"};
        }
        // Split roles by comma if multiple roles exist
        return user.getRole().split(",");
    }
}
