package io.divetrip.mapper.request;

import io.divetrip.domain.entity.Trip;
import io.divetrip.domain.entity.TripStatusHistory;
import io.divetrip.domain.entity.enumeration.TripStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TripStatusHistoryRequestMapper {

    @Mapping(source = "tripStatus", target = "tripStatus")
    @Mapping(source = "note", target = "note")
    @Mapping(source = "trip", target = "trip")
    TripStatusHistory toEntity(TripStatus tripStatus, String note, Trip trip);
}
