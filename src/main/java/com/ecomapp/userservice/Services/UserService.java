package com.ecomapp.userservice.Services;

import com.ecomapp.userservice.Models.User;
import com.ecomapp.userservice.Repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    public User signUp (String name,
                        String email,
                        String password) {
        Optional<User> checkUser = userRepository.findUserByEmail(email);
        if(checkUser.isPresent()) {
            throw new NoSuchElementException("username "+ email +
                    " already exist, Do you want to login ? ");
        }

        User newUser = new User();

        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(bCryptPasswordEncoder.encode(password));

        return userRepository.save(newUser);
    }

    public List<User> getAllUsers() {
        return userRepository.findAllBy();
    }
}
