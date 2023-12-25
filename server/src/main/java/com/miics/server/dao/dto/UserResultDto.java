package com.miics.server.dao.dto;

import com.miics.server.dao.models.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserResultDto {
    String name;
    Long isuNumber;
    String email;
    Role role;
    Long testsPassed;
    Double averageTestsResult;
    List<ResultDto> resultDtoList;
}
