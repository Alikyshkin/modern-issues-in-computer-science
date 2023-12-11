package com.miics.server.dao.mappers;

import com.miics.server.dao.dto.UserDto;
import com.miics.server.dao.models.User;
import org.mapstruct.AnnotateWith;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

import java.util.List;

@AnnotateWith( value = Component.class, elements = @AnnotateWith.Element( strings = "IUserMapperBean" ) )
@Mapper( componentModel = MappingConstants.ComponentModel.SPRING )
public interface IUserMapper {

    UserDto toDto(User user);

    List<UserDto> toDtos(List<User> users);

    User unDto(UserDto userDTO);

    List<User> unDtos(List<UserDto> userDTOs);
}