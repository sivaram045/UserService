package com.ecomapp.userservice.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Token extends BaseModel {
    //@Column(name = "Value")
    private String value;
    private Date expiryDate;
    @ManyToOne
    private User user;
}
