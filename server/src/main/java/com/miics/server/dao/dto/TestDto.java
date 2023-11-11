package com.miics.server.dao.dto;

import lombok.Value;

import java.io.Serializable;

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
}