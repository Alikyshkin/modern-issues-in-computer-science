package com.miics.server.dao.mappers;

import com.miics.server.dao.dto.FeedbackDto;
import com.miics.server.dao.models.Feedback;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IFeedbackMapper {

    FeedbackDto toDto(Feedback feedback);

    List<FeedbackDto> toDtos(List<Feedback> feedbacks);

    Feedback unDto(FeedbackDto feedbackDto);

    List<Feedback> unDtos(List<FeedbackDto> feedbackDtos);
}
