package io.divetrip.mapper.response;

import io.divetrip.domain.entity.TripLodging;
import io.divetrip.dto.response.TripResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TripLodgingResponseMapper {

    TripResponse.TripLodging toTripLodgingDto(final TripLodging tripLodging);

}
