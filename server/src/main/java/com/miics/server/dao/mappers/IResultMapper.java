package com.miics.server.dao.mappers;

import com.miics.server.dao.dto.ResultDto;
import com.miics.server.dao.models.Result;
import org.mapstruct.AnnotateWith;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

import java.util.List;

@AnnotateWith( value = Component.class, elements = @AnnotateWith.Element( strings = "IResultMapperBean" ) )
@Mapper( componentModel = MappingConstants.ComponentModel.SPRING )
public interface IResultMapper {

    ResultDto toDto(Result result);

    List<ResultDto> toDtos(List<Result> results);

    Result unDto(ResultDto resultDto);

    List<Result> unDtos(List<ResultDto> resultDtos);
}
