package com.miics.server.dao.mappers;


import com.miics.server.dao.dto.QuestionDto;
import com.miics.server.dao.models.Question;
import org.mapstruct.AnnotateWith;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

//@AnnotateWith( value = Component.class, elements = @AnnotateWith.Element( strings = "IQuestionMapperBean" ) )
//@Mapper( componentModel = MappingConstants.ComponentModel.SPRING )
//public interface IQuestionMapper {
//
//    QuestionDto toDto(Question question);
//
//    List<QuestionDto> toDtos(List<Question> questions);
//
//    Question toEntity(QuestionDto questionDto);
//
//    List<Question> unDtos(List<QuestionDto> questionDtos);
//}
