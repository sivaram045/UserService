package com.ecomapp.userservice.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    private String Email;
    private String Password;
}
