package io.divetrip.application.mapper.response;

import io.divetrip.application.dto.response.TripReservationStatusHistoryResponse;
import io.divetrip.library.domain.entity.TripReservationStatusHistory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TripReservationStatusHistoryResponseMapper {

    TripReservationStatusHistoryResponse.TripReservationStatusHistorys toListDto(TripReservationStatusHistory tripReservationStatusHistory);
}
