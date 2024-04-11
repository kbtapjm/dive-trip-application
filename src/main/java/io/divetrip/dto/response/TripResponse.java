package io.divetrip.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.divetrip.domain.entity.enumeration.TripStatus;
import io.divetrip.domain.entity.enumeration.VesselStatus;
import io.divetrip.dto.PageDto;
import io.divetrip.dto.request.TripRequest;
import jakarta.validation.constraints.NotNull;
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

public class TripResponse {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class Trips {
        /* 여행 ID */
        private UUID tripId;

        /* 여행 상태 */
        private TripStatus tripStatus;

        /* 출발 시간 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
        private LocalDateTime departureTime;

        /* 반환 시간 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
        private LocalDateTime returnTime;

        /* 시작 포트 */
        private String startPort;

        /* 종료 포트 */
        private String endPort;

        /* 기간 */
        private String durations;

        /* 다이빙 횟수 */
        private Integer totalDives;

        /* 국가 명 */
        private String countryName;

        /* 지역 */
        private String area;

        /* 선박 명 */
        private String vesselName;

        /* 선박 상태 */
        private VesselStatus vesselStatus;

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
    public static class TripResult {
        private List<Trips> content = List.of();
        private PageDto page;
        private TripRequest.SearchTrip search;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class Trip {
        /* 여행 ID */
        private UUID tripId;

        /* 여행 상태 */
        private TripStatus tripStatus;

        /* 출발 시간 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
        private LocalDateTime departureTime;

        /* 반환 시간 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
        private LocalDateTime returnTime;

        /* 시작 포트 */
        private String startPort;

        /* 종료 포트 */
        private String endPort;

        /* 기간 */
        private String durations;

        /* 다이빙 횟수 */
        private Integer totalDives;

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

        /* 여행 일정 */
        private List<TripSchedule> schedules = new ArrayList<>();

        /* 여행 숙소 */
        private List<TripLodging> lodgings = new ArrayList<>();

        /* 여행 상태 이력 */
        private List<TripStatusHistory> statusHistorys = new ArrayList<>();
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class TripSchedule {
        /* 여행 일정 ID */
        private UUID tripScheduleId;

        /* 여행 일자 */
        @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+9")
        private LocalDate tripDate;

        /* 여행 여정 */
        private String itinerary;

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
    public static class TripLodging {
        /* 여행 숙소 ID */
        private UUID tripLodgingId;

        /* 선박 선실 ID */
        private UUID vesselCabinId;

        /* 수용 인원 */
        @NotNull
        private Integer capacity;

        /* 선실 가격 */
        private Integer cabinPrice;

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
    public static class TripStatusHistory {
        /* 여행 상태 이력 ID */
        private UUID tripStatusHistoryId;

        /* 여행 상태 */
        private TripStatus tripStatus;

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

}
