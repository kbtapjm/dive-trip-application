package io.divetrip.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

public class ResourceRequest {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    @ToString
    public static class CreateResource {
        /* 그룹 ID */
        private UUID groupId;

        /* 리소스 명 */
        @NotBlank
        private String resourceName;

        /* 리소스 URL */
        @NotBlank
        private String resourceUrl;

        /* 리소스 설명 */
        private String resourceDesc;

        /* 리소스 순서 */
        private Integer resourceOrder;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    @ToString
    public static class UpdateResource {
        /* 그룹 ID */
        private UUID groupId;

        /* 리소스 명 */
        @NotBlank
        private String resourceName;

        /* 리소스 URL */
        @NotBlank
        private String resourceUrl;

        /* 리소스 설명 */
        private String resourceDesc;

        /* 리소스 순서 */
        private Integer resourceOrder;
    }

}
