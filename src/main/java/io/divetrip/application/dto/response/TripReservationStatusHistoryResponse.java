package io.divetrip.application.dto.response;

import io.divetrip.domain.entity.enumeration.ReservationStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

public class TripReservationStatusHistoryResponse {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class TripReservationStatusHistorys {
        /* 여행 예약 상태 이력 ID */
        private UUID tripReservationStatusHistoryId;

        /* 예약 상태 */
        private ReservationStatus reservationStatus;

        /* 비고 */
        private String note;
    }
}
