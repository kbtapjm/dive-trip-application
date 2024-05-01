package io.divetrip.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.divetrip.domain.entity.enumeration.ReservationStatus;
import io.divetrip.dto.PageDto;
import io.divetrip.dto.request.TripReservationRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class TripReservationResponse {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class TripReservations {
        /* 여행 예약 ID */
        private UUID tripReservationId;

        /* 예약 상태 */
        private ReservationStatus reservationStatus;

        /* 결제 여부 */
        private Boolean paid;

        /* 출발 편명 */
        private String departureFlightNumbers;

        /* 출발 시간 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
        private LocalDateTime departureFlightDate;

        /* 도착 편명 */
        private String arrivalFlightNumbers;

        /* 도착 시간 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
        private LocalDateTime arrivalFlightDate;

        /* 마지막 다이브 날짜 */
        @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+9")
        private LocalDate lastDiveDate;

        /* 약관 동의 여부 */
        private Boolean agreeTerms;

        /* 비고 */
        private String note;

        /* 등록자 */
        private String createdBy;

        /* 등록일 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
        private LocalDateTime createdAt;

        /* 수정자 */
        private String updatedBy;

        /* 수정일 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
        private LocalDateTime updatedAt;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class TripReservation {
        /* 여행 예약 ID */
        private UUID tripReservationId;

        /* 예약 상태 */
        private ReservationStatus reservationStatus;

        /* 결제 여부 */
        private Boolean paid;

        /* 출발 편명 */
        private String departureFlightNumbers;

        /* 출발 시간 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
        private LocalDateTime departureFlightDate;

        /* 도착 편명 */
        private String arrivalFlightNumbers;

        /* 도착 시간 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
        private LocalDateTime arrivalFlightDate;

        /* 마지막 다이브 날짜 */
        @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+9")
        private LocalDate lastDiveDate;

        /* 약관 동의 여부 */
        private Boolean agreeTerms;

        /* 비고 */
        private String note;

        /* 등록자 */
        private String createdBy;

        /* 등록일 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
        private LocalDateTime createdAt;

        /* 수정자 */
        private String updatedBy;

        /* 수정일 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
        private LocalDateTime updatedAt;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class TripReservationResult {
        private List<TripReservations> content = List.of();
        private PageDto page;
        private TripReservationRequest.SearchTripReservation search;
    }
}
