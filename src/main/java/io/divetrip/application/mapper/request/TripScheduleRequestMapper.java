package io.divetrip.application.mapper.request;

import io.divetrip.application.dto.request.TripRequest;
import io.divetrip.application.mapper.GenericMapper;
import io.divetrip.library.domain.entity.Trip;
import io.divetrip.library.domain.entity.TripSchedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TripScheduleRequestMapper extends GenericMapper<TripRequest.CreateTripSchedule, TripSchedule> {

    @Mapping(source = "trip", target = "trip")
    TripSchedule toEntity(TripRequest.CreateTripSchedule createTripSchedule, Trip trip);
}
