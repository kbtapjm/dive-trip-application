package io.divetrip.mapper.response;

import io.divetrip.domain.entity.TripSchedule;
import io.divetrip.dto.response.TripResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TripScheduleResponseMapper {

    TripResponse.TripSchedule toTripScheduleDto(final TripSchedule tripSchedule);

}
