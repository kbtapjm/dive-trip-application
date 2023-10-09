package io.divetrip.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.divetrip.domain.entity.enumeration.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

public class DiverRequestDto {

    @Setter
    @Getter
    @NoArgsConstructor
    public static class DiverCreate {
        /* 이메일 */
        @Email
        @NotBlank
        private String email;

        /* 비밀번호 */
        @NotBlank
        private String password;

        /* 비밀번호 확인 */
        @NotBlank
        private String passwordConfirm;

        /* 성 */
        @NotBlank
        private String familyName;

        /* 이름 */
        @NotBlank
        private String givenName;

        /* 성별 */
        @NotNull
        private Gender gender;

        /* 생일 */
        @NotNull
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate birthday;

        /* 국적 */
        private String nationality;

        /* 국가 코드 */
        private String countryCode;

        /* 연락처 */
        private String contactNumber;

        /* 여권 번호 */
        @NotBlank
        private String passportNo;

        /* 여권 만료일 */
        @NotNull
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate passportExpiryDate;

        /* 라이센스 여부 */
        @NotNull
        private Boolean licensed;
    }

    public static class DiverUpdate {

    }
}