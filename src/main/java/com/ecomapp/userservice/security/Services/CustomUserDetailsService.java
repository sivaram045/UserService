package com.ecomapp.userservice.security.Services;

import com.ecomapp.userservice.Models.User;
import com.ecomapp.userservice.Repositories.UserRepository;
import com.ecomapp.userservice.security.Models.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findUserByEmail(username);

        if(optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User by email: " + username + "doesn't exist..!");
        }

        CustomUserDetails userDetails = new CustomUserDetails(optionalUser.get());

        return userDetails;
    }
}
