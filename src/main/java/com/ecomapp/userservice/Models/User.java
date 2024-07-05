package com.ecomapp.userservice.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class User extends BaseModel {
    private String Name;
    @Column(unique = true)
    private String Email;
    private String Password;
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Role> roles;
    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Token> token;
}
