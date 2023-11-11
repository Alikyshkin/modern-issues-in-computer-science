package com.miics.server.dao.mappers;

import com.miics.server.dao.dto.ResultDto;
import com.miics.server.dao.models.Result;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IResultMapper {

    ResultDto toDto(Result result);

    List<ResultDto> toDtos(List<Result> results);

    Result unDto(ResultDto resultDto);

    List<Result> unDtos(List<ResultDto> resultDtos);
}
