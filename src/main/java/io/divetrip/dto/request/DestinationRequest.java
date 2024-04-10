package io.divetrip.dto.request;

import io.divetrip.domain.entity.enumeration.Continent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class DestinationRequest {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class CreateDestination {
        /* 대륙 */
        @NotNull
        private Continent continent;

        /* 지역 */
        @NotBlank
        @Size(min = 1, max = 30)
        private String area;

        /* 설명 */
        @NotBlank
        @Size(min = 1, max = 1000)
        private String description;

        /* 국가 ID */
        @NotNull
        private Long countryId;

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UpdateDestination {
        /* 대륙 */
        @NotNull
        private Continent continent;

        /* 지역 */
        @NotBlank
        @Size(min = 1, max = 30)
        private String area;

        /* 설명 */
        @NotBlank
        @Size(min = 1, max = 1000)
        private String description;

        /* 국가 ID */
        @NotNull
        private Long countryId;

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class SearchDestination {
        /* 대륙 */
        private Continent continent;

        /* 지역 */
        private String area;

        /* 국가 ID */
        private Long countryId;
    }
}
