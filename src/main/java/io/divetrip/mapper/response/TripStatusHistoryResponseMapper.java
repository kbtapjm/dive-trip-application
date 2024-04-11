package io.divetrip.mapper.response;

import io.divetrip.domain.entity.TripStatusHistory;
import io.divetrip.dto.response.TripResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TripStatusHistoryResponseMapper {

    TripResponse.TripStatusHistory toStatusHistoryDto(final TripStatusHistory tripStatusHistory);


}
