package io.divetrip.mapper.response;

import io.divetrip.domain.entity.Diver;
import io.divetrip.dto.response.DiverResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DiverResponseMapper {

    DiverResponse.Divers toDiversDto(Diver diver);

    DiverResponse.Diver toDiverDto(Diver diver);

}
