package com.miics.server.dao.mappers;

import com.miics.server.dao.dto.TestDto;
import com.miics.server.dao.models.Test;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ITestMapper {

    TestDto toDto(Test test);

    List<TestDto> toDtos(List<Test> tests);

    Test unDto(TestDto testDto);

    List<Test> unDtos(List<TestDto> testDtos);
}
