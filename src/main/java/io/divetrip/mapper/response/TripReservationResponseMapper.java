package io.divetrip.mapper.response;

import io.divetrip.domain.entity.TripReservation;
import io.divetrip.domain.repository.dto.response.TripReservationQueryResponse;
import io.divetrip.dto.response.TripReservationResponse;
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
