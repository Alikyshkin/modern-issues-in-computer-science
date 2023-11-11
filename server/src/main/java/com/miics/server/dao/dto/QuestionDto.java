package com.miics.server.dao.dto;

import com.miics.server.dao.models.Test;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.miics.server.dao.models.Question}
 */
@Value
public class QuestionDto implements Serializable {
    Long id;
    String question;
    String type;
    Test test;
}