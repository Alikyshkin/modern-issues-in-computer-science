package com.miics.server.dao.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.miics.server.dao.models.Test}
 */
@Data
public class TestDto implements Serializable {
    Long id;
    String name;
    String description;
    int duration;
    String subject;
    List<QuestionDto> questions;
}