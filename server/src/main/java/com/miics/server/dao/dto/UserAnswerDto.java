package com.miics.server.dao.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserAnswerDto {
    Long id;
    String title;
    List<String> userAnswers;
    Boolean passed;
}
