package com.miics.server.dao.mappers;


import com.miics.server.dao.dto.QuestionDto;
import com.miics.server.dao.models.*;
import org.mapstruct.AnnotateWith;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@AnnotateWith( value = Component.class, elements = @AnnotateWith.Element( strings = "IQuestionMapperBean" ) )
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface IQuestionMapper {

    QuestionDto toDto(Question question);

    default List<QuestionDto> toDtos(List<Question> questions){
        List<QuestionDto> questionDtos = new ArrayList<>();
        for(Question question : questions){
            QuestionDto questionDto = new QuestionDto();
            questionDto.setId(question.getId());
            questionDto.setTitle(question.getTitle());
            questionDto.setContent(question.getContent());
            questionDto.setType(question.getType());
            questionDto.setAnswers(question.getAnswers());
            questionDtos.add(questionDto);
        }
        return questionDtos;
    }

    Question unDto(QuestionDto questionDto);


    List<Question> unDtos(List<QuestionDto> questionDtos);
}
