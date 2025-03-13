package io.divetrip.application.mapper.response;

import io.divetrip.application.dto.response.TripResponse;
import io.divetrip.library.domain.entity.TripLodging;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TripLodgingResponseMapper {

    TripResponse.TripLodging toTripLodgingDto(final TripLodging tripLodging);

}
