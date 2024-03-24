package io.divetrip.mapper.request;

import io.divetrip.domain.entity.Trip;
import io.divetrip.dto.request.TripRequest;
import io.divetrip.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TripCreateRequestMapper extends GenericMapper<TripRequest.CreateTrip, Trip> {

}
