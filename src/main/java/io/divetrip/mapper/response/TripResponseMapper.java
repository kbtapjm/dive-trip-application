package io.divetrip.mapper.response;

import io.divetrip.domain.entity.Trip;
import io.divetrip.domain.repository.dto.response.TripQueryResponse;
import io.divetrip.dto.response.TripResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TripResponseMapper {

    TripResponse.Trips toTripsDto(final TripQueryResponse tripQueryResponse);

    @Mapping(source = "schedules", target = "schedules")
    @Mapping(source = "lodgings", target = "lodgings")
    @Mapping(source = "statusHistorys", target = "statusHistorys")
    TripResponse.Trip toTripDto(final Trip trip,
                                List<TripResponse.TripSchedule> schedules,
                                List<TripResponse.TripLodging> lodgings,
                                List<TripResponse.TripStatusHistory> statusHistorys);

}
