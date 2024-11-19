package com.ecomapp.userservice.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @OneToMany(mappedBy ="user",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Role> roles;
    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Token> token;
}
