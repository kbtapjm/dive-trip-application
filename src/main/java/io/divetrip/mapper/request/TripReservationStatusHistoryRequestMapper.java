package io.divetrip.mapper.request;

import io.divetrip.domain.entity.TripReservation;
import io.divetrip.domain.entity.TripReservationStatusHistory;
import io.divetrip.domain.entity.enumeration.ReservationStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TripReservationStatusHistoryRequestMapper {

    @Mapping(source = "reservationStatus", target = "reservationStatus")
    @Mapping(source = "note", target = "note")
    @Mapping(source = "tripReservation", target = "tripReservation")
    TripReservationStatusHistory toEntity(ReservationStatus reservationStatus, String note, TripReservation tripReservation);
}
