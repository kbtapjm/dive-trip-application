package io.divetrip.application.mapper.response;

import io.divetrip.application.dto.response.TripResponse;
import io.divetrip.library.domain.entity.TripStatusHistory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TripStatusHistoryResponseMapper {

    TripResponse.TripStatusHistory toStatusHistoryDto(final TripStatusHistory tripStatusHistory);


}
