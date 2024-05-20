package com.ecomapp.userservice.Controllers;

import com.ecomapp.userservice.DTOs.LoginDTO;
import com.ecomapp.userservice.DTOs.LogoutDTO;
import com.ecomapp.userservice.DTOs.SignUpDTO;
import com.ecomapp.userservice.Models.Token;
import com.ecomapp.userservice.Models.User;
import com.ecomapp.userservice.Services.UserService;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController (UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public User signUp (@RequestBody SignUpDTO request) {
        String name = request.getName();
        String email = request.getEmail();
        String password = request.getPassword();

        return userService.signUp(name, email, password);
    }
    @PostMapping("/login")
    public Token login (@RequestBody LoginDTO request) {
        return userService.login(request.getEmail(), request.getPassword());
    }
    @PostMapping("/logout")

    public String logout (@RequestBody LogoutDTO request) {
        return userService.logout(request.getValue());

    }
    @PostMapping("/validate/{token}")
    public User validate (@RequestBody String token) {
        return userService.validateToken(token);
    }

    @GetMapping()
    public List<User> getAllUsers () {
        return userService.getAllUsers();
    }
}
