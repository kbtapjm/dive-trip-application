package io.divetrip.dto.request;

import io.divetrip.domain.entity.enumeration.VesselStatus;
import io.divetrip.dto.SortDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class VesselRequest {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class CreateVessel {
        /* 선박 명 */
        @NotBlank
        @Size(min = 1, max = 50)
        private String vesselName;

        /* 선박 상태 */
        @NotNull
        private VesselStatus vesselStatus;

        /* 총 손님수 */
        private int totalGuests;

        /* 승무원 */
        private int crew;

        /* 캐빈 수 */
        private int numberCabins;

        /* 길이 */
        private String length;

        /* 넓이 */
        private String width;

        /* 선체 */
        private String hull;

        /* 순항 속도 */
        private String crusingSpeed;

        /* 엔진 */
        private String engine;

        /* 발전기 */
        private String generator;

        /* 압축기 */
        private String compressor;

        /* 나이트록스 */
        private String nitrox;

        /* 작은배 */
        private String dinghy;

        /* 워터 메이커 */
        private String waterMakers;

        /* 담수 탱크 */
        private String freshWaterTank;

        /* 디젤 탱크 */
        private String dieselTank;

        /* 범위 */
        private String range;

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UpdateVessel {
        /* 선박 명 */
        @NotBlank
        @Size(min = 1, max = 50)
        private String vesselName;

        /* 선박 상태 */
        @NotNull
        private VesselStatus vesselStatus;

        /* 총 손님수 */
        private int totalGuests;

        /* 승무원 */
        private int crew;

        /* 캐빈 수 */
        private int numberCabins;

        /* 길이 */
        private String length;

        /* 넓이 */
        private String width;

        /* 선체 */
        private String hull;

        /* 순항 속도 */
        private String crusingSpeed;

        /* 엔진 */
        private String engine;

        /* 발전기 */
        private String generator;

        /* 압축기 */
        private String compressor;

        /* 나이트록스 */
        private String nitrox;

        /* 작은배 */
        private String dinghy;

        /* 워터 메이커 */
        private String waterMakers;

        /* 담수 탱크 */
        private String freshWaterTank;

        /* 디젤 탱크 */
        private String dieselTank;

        /* 범위 */
        private String range;

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
        }

    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class changeVesselUsed {
        /* 사용 여부 */
        @NotNull
        private Boolean used;

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @SuperBuilder
    @AllArgsConstructor
    @ToString(callSuper = true)
    public static class SearchVessel extends SortDto {
        /* 선박 명 */
        private String vesselName;

        /* 선박 상태 */
        private VesselStatus vesselStatus;

        /* 사용 여부 */
        private Boolean used;
    }

}
