package com.miics.server.service;

import com.miics.server.dao.dto.UserDto;
import com.miics.server.dao.models.User;

import java.util.List;

public interface IUserService {
    public UserDto register(UserDto userDto) ;

    public UserDto login(UserDto userDto) ;

    public UserDto getUserById(Long userId) ;

    public List<UserDto> getUsers() ;

    public UserDto addUser(UserDto userDto) ;

    public UserDto deleteUser(UserDto userDto) ;

    public UserDto deleteUserById(Long userId);
}
