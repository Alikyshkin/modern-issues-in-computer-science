//package com.miics.server.service;
//
//import com.miics.server.dao.models.User;
//import lombok.Data;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.List;
//
//@Data
//public class UserSecurity {
//    private final String userName;
//    private final String userPassword;
//    private final List<SimpleGrantedAuthority> authorities;
//
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    public String getPassword() {
//        return userPassword;
//    }
//
//    public String getUsername() {
//        return userName;
//    }
//
//    public static UserDetails fromUser(User user) {
//        return new org.springframework.security.core.userdetails.User(
//                user.getUserName(), user.getPassword(),
//                user.getRole().grantedAuthorities()
//        );
//    }
//}
