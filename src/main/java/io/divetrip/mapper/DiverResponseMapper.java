package io.divetrip.mapper;

import io.divetrip.domain.entity.Diver;
import io.divetrip.dto.response.DiverResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DiverResponseMapper {

    DiverResponseDto.Divers toDiversDto(Diver diver);

    DiverResponseDto.Diver toDiverDto(Diver diver);

}
