package io.divetrip.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.divetrip.domain.dto.AddressDto;
import io.divetrip.dto.PageDto;
import io.divetrip.dto.request.DiverRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class DiverResponse {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class Divers {
        /* 다이버 ID */
        private UUID diverId;

        /* 이메일 */
        private String email;

        /* 성 */
        private String familyName;

        /* 이름 */
        private String givenName;

        /* 성별 */
        private String gender;

        /* 생일 */
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate birthday;

        /* 국적 */
        private String nationality;

        /* 국가 코드 */
        private String countryCode;

        /* 연락처 */
        private String contactNumber;

        /* 여권 번호 */
        private String passportNo;

        /* 여권 만료일 */
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate passportExpiryDate;

        /* 라이센스 여부 */
        private Boolean licensed;

        /* 주소 */
        private AddressDto address;

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
    public static class Diver {
        /* 다이버 ID */
        private UUID diverId;

        /* 이메일 */
        private String email;

        /* 성 */
        private String familyName;

        /* 이름 */
        private String givenName;

        /* 성별 */
        private String gender;

        /* 생일 */
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate birthday;

        /* 국적 */
        private String nationality;

        /* 국가 코드 */
        private String countryCode;

        /* 연락처 */
        private String contactNumber;

        /* 여권 번호 */
        private String passportNo;

        /* 여권 만료일 */
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate passportExpiryDate;

        /* 라이센스 여부 */
        private Boolean licensed;

        /* 주소 */
        private AddressDto address;

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

    /**
     * {
     *   "pageable": {
     *     "pageNumber": 0,
     *     "pageSize": 10,
     *     "sort": {
     *       "empty": false,
     *       "sorted": true,
     *       "unsorted": false
     *     },
     *     "offset": 0,
     *     "paged": true,
     *     "unpaged": false
     *   },
     *   "last": true,
     *   "totalPages": 1,
     *   "totalElements": 3,
     *   "size": 10,
     *   "number": 0,
     *   "sort": {
     *     "empty": false,
     *     "sorted": true,
     *     "unsorted": false
     *   },
     *   "first": true,
     *   "numberOfElements": 3,
     *   "empty": false
     * }
     */
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class DiverList {

        /* 컨텐트 */
        private List<Divers> content = List.of();

        /* Page 정보 */
        private PageDto page;

        /* Search 정보 */
        private DiverRequest.SearchDiver search;

    }
}
