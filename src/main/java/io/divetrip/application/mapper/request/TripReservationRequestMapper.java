package io.divetrip.application.mapper.request;

import io.divetrip.application.dto.request.TripReservationRequest;
import io.divetrip.application.mapper.GenericMapper;
import io.divetrip.library.domain.entity.Diver;
import io.divetrip.library.domain.entity.TripLodging;
import io.divetrip.library.domain.entity.TripReservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TripReservationRequestMapper extends GenericMapper<TripReservationRequest.CreateTripReservation, TripReservation> {

    @Mapping(source = "tripLodging", target = "tripLodging")
    @Mapping(source = "diver", target = "diver")
    @Mapping(source = "dto.note", target = "note")
    TripReservation toEntity(TripReservationRequest.CreateTripReservation dto, Diver diver, TripLodging tripLodging);

}
