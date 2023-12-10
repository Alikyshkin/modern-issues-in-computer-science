package com.miics.server.service;

import com.miics.server.dao.dto.UserDto;
import com.miics.server.dao.mappers.IUserMapper;
import com.miics.server.dao.models.User;
import com.miics.server.dao.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final IUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final IUserMapper userMapper;

    @Autowired
    public UserService(IUserRepository userRepository, PasswordEncoder passwordEncoder, IUserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public UserDto register(UserDto userDto) {
        User user = userMapper.unDto(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    public UserDto login(UserDto userDto) {
        User user = userRepository.findByUserName(userDto.getUserName())
                .orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));;
        if (user != null && passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            return userMapper.toDto(user);
        }
        throw new RuntimeException("Invalid username or password");
    }

    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toDto(user);
    }

    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toDtos(users);
    }

    public UserDto addUser(UserDto userDto) {
        User user = userMapper.unDto(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    public UserDto deleteUser(UserDto userDto) {
        User user = userRepository.findByUserName(userDto.getUserName())
                .orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));
        if (user != null) {
            userRepository.delete(user);
            return userMapper.toDto(user);
        }
        throw new RuntimeException("User not found");
    }

    public UserDto deleteUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
        return userMapper.toDto(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));
        return UserSecurity.fromUser(user);
    }
}
