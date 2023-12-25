package com.miics.server.dao.mappers;

import com.miics.server.dao.dto.UserAnswerDto;
import com.miics.server.dao.models.UserAnswer;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

@AnnotateWith( value = Component.class, elements = @AnnotateWith.Element( strings = "IUserAnswerMapperBean" ) )
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface IUserAnswerMapper {
    UserAnswer toEntity(UserAnswerDto userAnswerDto);

    List<UserAnswer> toEntities(List<UserAnswerDto> userAnswerDtos);

    UserAnswerDto toDto(UserAnswer userAnswer);

    List<UserAnswerDto> toDtos(List<UserAnswer> userAnswers);
}