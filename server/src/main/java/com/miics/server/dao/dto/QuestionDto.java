package com.miics.server.dao.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.miics.server.dao.models.Question}
 */

@Data
public class QuestionDto implements Serializable {
    Long id;
    String title;
    String content;
    String type;
    List<String> answers;
    List<Boolean> isCorrect;
}