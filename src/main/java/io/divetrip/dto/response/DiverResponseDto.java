package io.divetrip.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class DiverResponseDto {

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

        /* 등록자 */
        private String createdBy;

        /* 등록일 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
        private LocalDateTime createdAt;

        /* 수정자 */
        private String updatedBy;

        /* 수정일 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
        private LocalDateTime updateAt;
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

        /* 등록자 */
        private String createdBy;

        /* 등록일 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
        private LocalDateTime createdAt;

        /* 수정자 */
        private String updatedBy;

        /* 수정일 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
        private LocalDateTime updateAt;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class DiversPage {
        private List<Divers> content = List.of();
    }

}
