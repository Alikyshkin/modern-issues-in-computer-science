package com.miics.server.dao.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserTestResultsDto {
    Long isuNumber;
    Long testId;
    String testTitle;
    int timeTaken;
    int correctAnswers;
    int wrongAnswers;
    int totalQuestions;
    List<UserAnswerDto> userAnswerDtos;
}
