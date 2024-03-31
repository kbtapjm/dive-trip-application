package io.divetrip.mapper.request;

import io.divetrip.domain.entity.Trip;
import io.divetrip.domain.entity.TripLodging;
import io.divetrip.dto.request.TripRequest;
import io.divetrip.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TripLodgingRequestMapper extends GenericMapper<TripRequest.CreateTripLodging, TripLodging> {

    @Mapping(source = "trip", target = "trip")
    TripLodging toEntity(TripRequest.CreateTripLodging createTripLodging, Trip trip);
}
