package com.miics.server.dao.mappers;


import com.miics.server.dao.dto.QuestionDto;
import com.miics.server.dao.models.MultipleQuestion;
import com.miics.server.dao.models.OpenQuestion;
import com.miics.server.dao.models.Question;
import com.miics.server.dao.models.QuestionType;
import org.mapstruct.AnnotateWith;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.miics.server.dao.models.QuestionType.MULTIPLE_CHOICE;
import static com.miics.server.dao.models.QuestionType.ONE_CHOICE;

@AnnotateWith( value = Component.class, elements = @AnnotateWith.Element( strings = "IQuestionMapperBean" ) )
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface IQuestionMapper {

    QuestionDto toDto(Question question);

    List<QuestionDto> toDtos(List<Question> questions);

    default Question toEntity(QuestionDto questionDto) {
        if (questionDto == null) {
            return null;
        }

        if (questionDto.getType() == ONE_CHOICE) {
            OpenQuestion openQuestion = new OpenQuestion();

            openQuestion.setId(questionDto.getId());
            openQuestion.setTitle(questionDto.getTitle());
            openQuestion.setContent(questionDto.getContent());
            openQuestion.setAnswer(questionDto.getAnswer());
            openQuestion.setAnswerType(questionDto.getType());

            return openQuestion;
        } else {
            MultipleQuestion multipleQuestion = new MultipleQuestion();

            multipleQuestion.setId(questionDto.getId());
            multipleQuestion.setTitle(questionDto.getTitle());
            multipleQuestion.setContent(questionDto.getContent());
            multipleQuestion.setAnswer(questionDto.getAnswer());
            multipleQuestion.setAnswerType(questionDto.getType());
            multipleQuestion.setOptions(questionDto.getOptions());

            return multipleQuestion;
        }
    }


    default List<Question> toEntities(List<QuestionDto> questionDtos) {
        if (questionDtos == null) {
            return null;
        }

        List<Question> questions = new ArrayList<>();
        for (QuestionDto questionDto : questionDtos) {
            questions.add(toEntity(questionDto));
        }

        return questions;
    }
}
