package com.miics.server.dao.dto;

import com.miics.server.dao.models.Option;
import com.miics.server.dao.models.QuestionType;
import com.miics.server.dao.models.Test;
import lombok.Data;
import lombok.Value;

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
    QuestionType type;
    String answer;
    Boolean isCorrect;
    List<Option> options;
}