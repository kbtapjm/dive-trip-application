package io.divetrip.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CountryResponse {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class Country {
        /* 국가 ID */
        private Long countryId;

        /* ISO */
        private String iso;

        /* 국가 명 */
        private String countryName;

        /* ISO3 문자 */
        private String iso3Char;

        /* ISO3 숫자 */
        private Integer iso3Digit;

        /* 전화 번호 코드 */
        private Integer callingCode;
    }
}
