package com.miics.server.dao.dto;

import lombok.Data;

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