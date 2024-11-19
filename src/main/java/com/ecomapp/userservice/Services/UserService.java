package com.ecomapp.userservice.Services;

import com.ecomapp.userservice.DTOs.RoleDTO;
import com.ecomapp.userservice.Models.Role;
import com.ecomapp.userservice.Models.Token;
import com.ecomapp.userservice.Models.User;
import com.ecomapp.userservice.Repositories.TokenRepository;
import com.ecomapp.userservice.Repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenRepository tokenRepository;

    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder,
                       TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepository = tokenRepository;
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

    public Token login (String email, String password) {
        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        if(optionalUser.isEmpty()) {
            //throw user not exist exception
            return null;
        }
        User user = optionalUser.get();
        if(!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            //throw pw not match exception
            return null;
        }

        Token token = getToken(user);

        return tokenRepository.save(token);
    }

    private static Token getToken(User user) {
        LocalDate today = LocalDate.now();
        LocalDate thirtyDaysLater = today.plus(30, ChronoUnit.DAYS);

        // Convert LocalDate to Date
        Date expiryDate = Date.from(thirtyDaysLater.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Token token = new Token();
        token.setUser(user);
        token.setExpiryDate(expiryDate);
        token.setValue(RandomStringUtils.randomAlphanumeric(128));
        return token;
    }


    public String logout(String value) {
        Optional<Token> optionalToken1 = tokenRepository.findByValue(value); //not using
        Optional<Token> optionalToken = tokenRepository.findByValueAndDeletedEquals(value, false);

        if(optionalToken.isEmpty()) {
            //throw invalid token exception
            throw new NoSuchElementException("invalid token");
        }else if(optionalToken.get().isDeleted()) {
            //throw session expired exception
            throw new NoSuchElementException("session expired, login again");
        }

        Token token1 = optionalToken.get();
        token1.setDeleted(true);
        tokenRepository.save(token1);

        return "Logout Successful";

    }

    public User validateToken(String token) {
        Optional<Token> optionalToken = tokenRepository.findByValueAndDeletedEquals(
                token,
                false);

        if(optionalToken.isEmpty()) {
            //throw invalid token exception
            return null;
        }
        return optionalToken.get().getUser();
    }

    public List<User> getAllUsers() {
        return userRepository.findAllBy();
    }

    public User updateUser(long id, RoleDTO roles) {
        Optional<User> optionalUser = userRepository.findUserById(id);
        if(optionalUser.isEmpty()) {
            throw new NoSuchElementException("User by id: " + id + " doesn't exist..!");
        }
        User user = optionalUser.get();
        List<Role> roleList = new ArrayList<>();
        for(String role: roles.getRoles()) {
            Role newRole = new Role();
            //newRole.setRole(role);
            newRole.setTitle(role);
            roleList.add(newRole);
        }
        user.setRoles(roleList);
        return userRepository.save(user);
    }
}
