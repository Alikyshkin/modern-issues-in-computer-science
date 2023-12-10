package com.miics.server.dao.dto;

import com.miics.server.dao.models.Question;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.miics.server.dao.models.Test}
 */
@Value
public class TestDto implements Serializable {
    Long id;
    String name;
    String description;
    int duration;
    String subject;
    List<Question> questions;
}