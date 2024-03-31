package io.divetrip.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.divetrip.domain.entity.enumeration.TripStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TripRequest {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class CreateTrip {
        /* 여행 상태 */
        @NotNull
        private TripStatus tripStatus;

        /* 출발 시간 */
        @NotNull
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
        private LocalDateTime departureTime;

        /* 반환 시간 */
        @NotNull
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
        private LocalDateTime returnTime;

        /* 시작 포트 */
        @NotBlank
        @Size(min = 1, max = 20)
        private String startPort;

        /* 종료 포트 */
        @NotBlank
        @Size(min = 1, max = 20)
        private String endPort;

        /* 기간 */
        @NotBlank
        @Size(min = 1, max = 20)
        private String durations;

        /* 다이빙 횟수 */
        @NotNull
        private Integer totalDives;

        /* 목적지 ID */
        @NotNull
        private UUID destinationId;

        /* 선박 ID */
        @NotNull
        private UUID vesselId;

        /* 여행 일정 */
        @Valid
        @NotNull
        private List<CreateTripSchedule> schedules = new ArrayList<>();

        /* 여행 숙소 */
        @Valid
        @NotNull
        private List<CreateTripLodging> lodgings = new ArrayList<>();
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class CreateTripSchedule {
        /* 여행 일자 */
        @NotNull
        @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+9")
        private LocalDate tripDate;

        /* 여행 여정 */
        @NotBlank
        @Size(min = 1, max = 500)
        private String itinerary;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class CreateTripLodging {
        /* 선박 선실 ID */
        private UUID vesselCabinId;

        /* 수용 인원 */
        @NotNull
        private Integer capacity;

        /* 선실 가격 */
        @NotNull
        private Integer cabinPrice;

        /* 비고 */
        private String note;
    }


}
