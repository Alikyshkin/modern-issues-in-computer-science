package com.miics.server.dao.dto;

import com.miics.server.dao.models.Test;
import com.miics.server.dao.models.User;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

@Data
public class TeacherResultDto implements Serializable {
    Long testId;
    String testTitle;
    Double averageTestsResult;
    Long usersPassed;
    List<ResultDto> resultDtoList;
}