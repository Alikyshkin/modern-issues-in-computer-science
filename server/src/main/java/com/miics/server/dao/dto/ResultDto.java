package com.miics.server.dao.dto;

import com.miics.server.dao.models.Test;
import com.miics.server.dao.models.User;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.miics.server.dao.models.Result}
 */
@Value
public class ResultDto implements Serializable {
    Long id;
    int score;
    User user;
    Test test;
}