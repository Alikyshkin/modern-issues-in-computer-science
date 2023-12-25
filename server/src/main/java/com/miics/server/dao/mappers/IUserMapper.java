package com.miics.server.dao.mappers;

import com.miics.server.dao.dto.UserDto;
import com.miics.server.dao.models.User;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

@AnnotateWith( value = Component.class, elements = @AnnotateWith.Element( strings = "IUserMapperBean" ) )
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface IUserMapper {

    UserDto toDto(User user);

    List<UserDto> toDtos(List<User> users);

    User toEntity(UserDto userDTO);

    List<User> toEntities(List<UserDto> userDTOs);

}