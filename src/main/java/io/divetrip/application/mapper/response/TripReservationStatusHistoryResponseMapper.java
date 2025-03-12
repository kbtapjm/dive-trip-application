package io.divetrip.application.mapper.response;

import io.divetrip.domain.entity.TripReservationStatusHistory;
import io.divetrip.application.dto.response.TripReservationStatusHistoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TripReservationStatusHistoryResponseMapper {

    TripReservationStatusHistoryResponse.TripReservationStatusHistorys toListDto(TripReservationStatusHistory tripReservationStatusHistory);
}
