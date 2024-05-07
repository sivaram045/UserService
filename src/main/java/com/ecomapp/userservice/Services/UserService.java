package com.ecomapp.userservice.Services;

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
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Date;

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
        Optional<Token> optionalToken = tokenRepository.findByValue(value);

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

    public List<User> getAllUsers() {
        return userRepository.findAllBy();
    }
}
