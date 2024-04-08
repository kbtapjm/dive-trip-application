package io.divetrip.mapper.response;

import io.divetrip.domain.repository.dto.response.TripQueryResponse;
import io.divetrip.dto.response.TripResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TripResponseMapper {

    TripResponse.Trips toTripsDto(final TripQueryResponse tripQueryResponse);

}
