package io.divetrip.mapper.request;

import io.divetrip.domain.entity.Trip;
import io.divetrip.domain.entity.TripSchedule;
import io.divetrip.dto.request.TripRequest;
import io.divetrip.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TripScheduleRequestMapper extends GenericMapper<TripRequest.CreateTripSchedule, TripSchedule> {

    @Mapping(source = "trip", target = "trip")
    TripSchedule toEntity(TripRequest.CreateTripSchedule createTripSchedule, Trip trip);
}
