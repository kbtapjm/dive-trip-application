package io.divetrip.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.divetrip.domain.dto.AddressDto;
import io.divetrip.domain.entity.enumeration.Gender;
import io.divetrip.validator.valid.EnumValue;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;

public class AuthRequest {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class Login {
        /* 이메일 */
        @Email
        @NotBlank
        private String email;

        /* 비밀번호 */
        @NotBlank
        @Size(min = 6, max = 20)
        private String password;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class Signup {
        /* 이메일 */
        @Email
        @NotBlank
        private String email;

        /* 비밀번호 */
        @NotBlank
        @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%]).{6,20})", message = "{valid.diver.password.pattern}")
        @Size(min = 6, max = 20)
        private String password;

        /* 비밀번호 확인 */
        @NotBlank
        private String passwordConfirm;

        /* 성 */
        @NotBlank
        @Size(min = 1, max = 10)
        private String familyName;

        /* 이름 */
        @NotBlank
        @Size(min = 1, max = 25)
        private String givenName;

        /* 성별 */
        @NotBlank
        @EnumValue(enumClass = Gender.class, ignoreCase = true)
        private String gender;

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

        /* 주소 */
        private AddressDto address;

        @AssertTrue(message = "{valid.diver.passwordConfirm.notMatch}")
        public boolean isPasswordConfirm() {
            if (!StringUtils.isEmpty(this.password) && !StringUtils.isEmpty(this.passwordConfirm)) {
                return StringUtils.equals(this.password, this.passwordConfirm);
            }

            return false;
        }
    }
}
