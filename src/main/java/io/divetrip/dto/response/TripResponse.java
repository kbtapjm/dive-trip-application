package io.divetrip.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.divetrip.domain.entity.enumeration.TripStatus;
import io.divetrip.dto.PageDto;
import io.divetrip.dto.request.TripRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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

}
