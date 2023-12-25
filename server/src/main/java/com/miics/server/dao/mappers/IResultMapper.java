package com.miics.server.dao.mappers;

import com.miics.server.dao.dto.ResultDto;
import com.miics.server.dao.models.Result;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

@AnnotateWith( value = Component.class, elements = @AnnotateWith.Element( strings = "IResultMapperBean" ) )
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface IResultMapper {
    Result toEntity(ResultDto resultDto);

    ResultDto toDto(Result result);

    List<ResultDto> toDtos(List<Result> userTestResults);

    List<Result> toEntities(List<ResultDto> resultDtos);



}