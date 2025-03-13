package io.divetrip.application.mapper.response;

import io.divetrip.application.dto.response.DiverResponse;
import io.divetrip.library.domain.entity.Diver;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DiverResponseMapper {

    DiverResponse.Divers toDiversDto(Diver diver);

    DiverResponse.Diver toDiverDto(Diver diver);

}
