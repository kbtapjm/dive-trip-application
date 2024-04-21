package io.divetrip.mapper.response;

import io.divetrip.domain.repository.dto.response.TripReservationQueryResponse;
import io.divetrip.dto.response.TripReservationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TripReservationResponseMapper {

    TripReservationResponse.TripReservations toTripReservationsDto(final TripReservationQueryResponse tripReservationQueryResponse);

}
