package com.miics.server.service.configuration;

import com.miics.server.dao.models.User;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Data
public class UserSecurity {

    private final String userEmail;
    private final String userPassword;
    private final List<SimpleGrantedAuthority> authorities;

    public List<SimpleGrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getPassword() {
        return userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public static UserDetails fromUser(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(),
                user.getRole().grantedAuthorities()
        );
    }
}
