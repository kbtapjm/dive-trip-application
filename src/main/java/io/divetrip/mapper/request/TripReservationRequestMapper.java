package io.divetrip.mapper.request;

import io.divetrip.domain.entity.Diver;
import io.divetrip.domain.entity.TripLodging;
import io.divetrip.domain.entity.TripReservation;
import io.divetrip.dto.request.TripReservationRequest;
import io.divetrip.mapper.GenericMapper;
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
