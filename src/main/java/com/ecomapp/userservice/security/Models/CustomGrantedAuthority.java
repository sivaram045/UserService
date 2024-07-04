package com.ecomapp.userservice.security.Models;

import com.ecomapp.userservice.Models.Role;
import org.springframework.security.core.GrantedAuthority;

public class CustomGrantedAuthority implements GrantedAuthority {
    private String authority;
    public CustomGrantedAuthority(Role role) {
        this.authority = role.getTitle();
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
