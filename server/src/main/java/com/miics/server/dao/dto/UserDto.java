package com.miics.server.dao.dto;

import com.miics.server.dao.models.Role;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.miics.server.dao.models.User}
 */
@Value
public class UserDto implements Serializable {
    Long isuNumber;
    String userName;
    String email;
    String password;
    Role role;

}