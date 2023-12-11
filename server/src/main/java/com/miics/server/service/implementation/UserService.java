package com.miics.server.service.implementation;

import com.miics.server.dao.dto.UserDto;
import com.miics.server.dao.mappers.IUserMapper;
import com.miics.server.dao.models.UserMapper;
import com.miics.server.dao.models.User;
import com.miics.server.dao.repositories.IUserRepository;
import com.miics.server.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ComponentScan(basePackages = {"com.miics.server.dao.mappers", "com.miics.server.dao.repositories"})
public class UserService /*implements UserDetailsService*/ implements IUserService {

    @Autowired
    private IUserMapper userMapper;

    @Autowired
    private IUserRepository userRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto register(UserDto userDto) {
        User user = userMapper.unDto(userDto);
//        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public UserDto login(UserDto userDto) {
        User user = userRepository.findByUserName(userDto.getUserName());
//        if (user != null && passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
//            return userMapper.toDto(user);
//        }
        throw new RuntimeException("Invalid username or password");
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toDto(user);
    }

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toDtos(users);
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        User user = userMapper.unDto(userDto);
//        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public UserDto deleteUser(UserDto userDto) {
        User user = userRepository.findByUserName(userDto.getUserName());
        if (user != null) {
            userRepository.delete(user);
            return userMapper.toDto(user);
        }
        throw new RuntimeException("User not found");
    }

    @Override
    public UserDto deleteUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
        return userMapper.toDto(user);
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return null;
//    }
}
