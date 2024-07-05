package com.ecomapp.userservice.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Role extends BaseModel {
    private String Title;
    @ManyToMany(mappedBy = "roles" , cascade = CascadeType.PERSIST)
    @JsonBackReference
    private List<User> users;
}
