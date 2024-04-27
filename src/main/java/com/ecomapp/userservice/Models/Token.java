package com.ecomapp.userservice.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Token extends BaseModel {
    private String Value;
    private Date expiryDate;
    @ManyToOne
    private User user;
}
