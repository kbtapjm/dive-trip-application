package io.divetrip.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class RoleRequest {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class CreateRole {
        /* 역할 코드 */
        @NotBlank
        @Size(min = 1, max = 20)
        private String roleCode;

        /* 역할 명 */
        @NotBlank
        @Size(min = 1, max = 30)
        private String roleName;

        /* 비고 */
        private String note;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UpdateRole {
        /* 역할 명 */
        @NotBlank
        @Size(min = 1, max = 30)
        private String roleName;

        /* 비고 */
        private String note;
    }

}
