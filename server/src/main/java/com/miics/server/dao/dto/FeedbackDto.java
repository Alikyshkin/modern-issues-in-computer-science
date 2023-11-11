package com.miics.server.dao.dto;

import com.miics.server.dao.models.Test;
import com.miics.server.dao.models.User;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.miics.server.dao.models.Feedback}
 */
@Value
public class FeedbackDto implements Serializable {
    Long id;
    String feedback;
    User user;
    Test test;
}