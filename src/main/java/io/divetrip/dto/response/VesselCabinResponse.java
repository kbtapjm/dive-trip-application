package io.divetrip.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

public class VesselCabinResponse {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class VesselCabins {
        /* 선박 선실 ID */
        private UUID vesselCabinId;

        /* 선실 명 */
        private String cabinName;

        /* 설명 */
        private String description;

        /* 크기 */
        private String size;

        /* 최대 수용 인원 */
        private int maxOccupancy;

        /* 침구 */
        private String bedding;

        /* 실내 욕실 */
        private Boolean ensuiteBathroom;

        /* 에어컨 */
        private String aircon;

        /* 사용 여부 */
        private Boolean used;

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
    public static class VesselCabin {
        /* 선박 선실 ID */
        private UUID vesselCabinId;

        /* 선실 명 */
        private String cabinName;

        /* 설명 */
        private String description;

        /* 크기 */
        private String size;

        /* 최대 수용 인원 */
        private int maxOccupancy;

        /* 침구 */
        private String bedding;

        /* 실내 욕실 */
        private Boolean ensuiteBathroom;

        /* 에어컨 */
        private String aircon;

        /* 사용 여부 */
        private Boolean used;

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
