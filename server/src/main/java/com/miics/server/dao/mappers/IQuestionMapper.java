package com.miics.server.dao.mappers;


import com.miics.server.dao.dto.QuestionDto;
import com.miics.server.dao.models.Question;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IQuestionMapper {

    QuestionDto toDto(Question question);

    List<QuestionDto> toDtos(List<Question> questions);

    Question unDto(QuestionDto questionDto);

    List<Question> unDtos(List<QuestionDto> questionDtos);
}
