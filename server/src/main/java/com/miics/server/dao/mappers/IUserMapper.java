package com.miics.server.dao.mappers;

import com.miics.server.dao.dto.UserDto;
import com.miics.server.dao.models.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUserMapper {

    UserDto toDto(User user);

    List<UserDto> toDtos(List<User> users);

    User unDto(UserDto userDTO);

    List<User> unDtos(List<UserDto> userDTOs);
}