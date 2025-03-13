package io.divetrip.application.mapper.response;

import io.divetrip.application.dto.response.TripReservationResponse;
import io.divetrip.library.domain.entity.TripReservation;
import io.divetrip.library.domain.repository.dto.response.TripReservationQueryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TripReservationResponseMapper {

    TripReservationResponse.TripReservations toTripReservationsDto(final TripReservationQueryResponse tripReservationQueryResponse);

    @Mapping(source = "tripReservation.tripLodging.tripLodgingId", target = "tripLodgingId")
    @Mapping(source = "tripReservation.diver.diverId", target = "diverId")
    TripReservationResponse.TripReservation toTripReservationDto(final TripReservation tripReservation);

}
