package com.miics.server.dao.mappers;

import com.miics.server.dao.dto.TestDto;
import com.miics.server.dao.models.Test;
import org.mapstruct.AnnotateWith;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@AnnotateWith( value = Component.class, elements = @AnnotateWith.Element( strings = "ITestMapperBean" ) )
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ITestMapper {

    TestDto toDto(Test test);

    List<TestDto> toDtos(List<Test> tests);

    default Test unDto(TestDto testDto){
        Test test = new Test();
        test.setName(testDto.getName());
        test.setDuration(testDto.getDuration());
        test.setSubject(testDto.getSubject());
        test.setDescription(testDto.getDescription());
        return test;
    }

    List<Test> unDtos(List<TestDto> testDtos);
}
