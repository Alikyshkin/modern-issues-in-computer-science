package com.miics.server.dao.mappers;

import com.miics.server.dao.dto.TestDto;
import com.miics.server.dao.models.Test;
import org.mapstruct.AnnotateWith;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

@AnnotateWith( value = Component.class, elements = @AnnotateWith.Element( strings = "ITestMapperBean" ) )
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ITestMapper {

    TestDto toDto(Test test);

    List<TestDto> toDtos(List<Test> tests);

    Test unDto(TestDto testDto);

    List<Test> unDtos(List<TestDto> testDtos);
}
