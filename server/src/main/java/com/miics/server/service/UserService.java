package com.miics.server.service;

import com.miics.server.dao.dto.UserDto;
import com.miics.server.dao.mappers.IUserMapper;
import com.miics.server.dao.models.User;
import com.miics.server.dao.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ComponentScan(basePackages = {"com.miics.server.dao.mappers"})
public class UserService /*implements UserDetailsService*/{

    private final IUserMapper userMapper;

    private final IUserRepository userRepository;

    public UserService(IUserMapper userMapper, @Qualifier("UserRepositoryBean") IUserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public UserDto register(UserDto userDto) {
        User user = userMapper.unDto(userDto);
//        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
        return userMapper.toDto(user);
    }

//    public UserDto login(UserDto userDto) {
//        User user = userRepository.findByUserName(userDto.getUserName());
////        if (user != null && passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
////            return userMapper.toDto(user);
////        }
//        throw new RuntimeException("Invalid username or password");
//    }

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
//        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    public UserDto deleteUser(UserDto userDto) {
        User user = userRepository.findByUserName(userDto.getUserName());
        if (user != null) {
            userRepository.delete(user);
            return userMapper.toDto(user);
        }
        throw new RuntimeException("User not found");
    }

    public UserDto deleteUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
        return userMapper.toDto(user);
    }

}
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return null;
//    }