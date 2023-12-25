package com.miics.server.service.configuration;

import com.miics.server.dao.models.User;
import com.miics.server.dao.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    private final IUserRepository userRepository ;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        return  UserSecurity.fromUser(user) ;
    }

    public User findByIdUser(Long id) {
        return userRepository.getReferenceById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}