package com.ecomapp.userservice.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpDTO {
    private String Name;
    private String Email;
    private String Password;
}
