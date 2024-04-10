package io.divetrip.mapper.request;

import io.divetrip.domain.entity.Destination;
import io.divetrip.domain.entity.Trip;
import io.divetrip.domain.entity.Vessel;
import io.divetrip.dto.request.TripRequest;
import io.divetrip.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TripCreateRequestMapper extends GenericMapper<TripRequest.CreateTrip, Trip> {

    @Mapping(source = "destination", target = "destination")
    @Mapping(source = "vessel", target = "vessel")
    @Mapping(target = "schedules", ignore = true)
    @Mapping(target = "lodgings", ignore = true)
    Trip toEntity(TripRequest.CreateTrip createTrip, Destination destination, Vessel vessel);
}
