package io.divetrip.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class VesselCabinRequest {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class CreateVesselCabin {
        /* 선실 명 */
        @NotBlank
        @Size(min = 1, max = 50)
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

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UpdateVesselCabin {
        /* 선실 명 */
        @NotBlank
        @Size(min = 1, max = 50)
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

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
        }
    }
}
