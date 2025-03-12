package io.divetrip.application.mapper.response;

import io.divetrip.domain.entity.TripStatusHistory;
import io.divetrip.application.dto.response.TripResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TripStatusHistoryResponseMapper {

    TripResponse.TripStatusHistory toStatusHistoryDto(final TripStatusHistory tripStatusHistory);


}
