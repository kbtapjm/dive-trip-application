package io.divetrip.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.divetrip.domain.entity.enumeration.ReservationStatus;
import io.divetrip.dto.SearchDto;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class TripReservationRequest {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class CreateTripReservation {
        /* 예약 상태 */
        @NotNull
        private ReservationStatus reservationStatus;

        /* 결제 여부 */
        @NotNull
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
        @NotNull
        private Boolean agreeTerms;

        /* 비고 */
        private String note;

        /* 여행 숙소 ID */
        @NotNull
        private UUID tripLodgingId;

        /* 다이버 ID */
        @NotNull
        private UUID diverId;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class updateTripReservation {
        /* 예약 상태 */
        @NotNull
        private ReservationStatus reservationStatus;

        /* 결제 여부 */
        @NotNull
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
        @NotNull
        private Boolean agreeTerms;

        /* 비고 */
        private String note;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @SuperBuilder
    @AllArgsConstructor
    @ToString(callSuper = true)
    public static class SearchTripReservation extends SearchDto {
        /* 예약 상태 */
        private ReservationStatus reservationStatus;
    }

}
