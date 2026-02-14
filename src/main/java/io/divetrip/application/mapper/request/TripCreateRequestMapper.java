package io.divetrip.application.mapper.request;

import io.divetrip.application.dto.request.TripRequest;
import io.divetrip.application.mapper.GenericMapper;
import io.divetrip.library.domain.entity.Destination;
import io.divetrip.library.domain.entity.Trip;
import io.divetrip.library.domain.entity.Vessel;
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
