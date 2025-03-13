package io.divetrip.application.mapper.request;


import io.divetrip.application.dto.request.TripRequest;
import io.divetrip.library.domain.entity.Trip;
import io.divetrip.library.domain.entity.TripLodging;
import io.divetrip.library.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TripLodgingRequestMapper extends GenericMapper<TripRequest.CreateTripLodging, TripLodging> {

    @Mapping(source = "trip", target = "trip")
    TripLodging toEntity(TripRequest.CreateTripLodging createTripLodging, Trip trip);
}
