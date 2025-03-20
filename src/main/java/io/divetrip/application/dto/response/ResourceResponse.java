package io.divetrip.application.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ResourceResponse {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Resources {
        /* 리소스 ID */
        private UUID resourceId;

        /* 그룹 ID */
        private UUID groupId;

        /* 리소스 명 */
        private String resourceName;

        /* 리소스 URL */
        private String resourceUrl;

        /* 리소스 설명 */
        private String resourceDesc;

        /* 리소스 순서 */
        private Integer resourceOrder;

        /* 사용 여부 */
        private Boolean used;

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

        /* 하위 리소스 목록 */
        @Builder.Default
        private List<Resource> subResources = List.of();
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class Resource {
        /* 리소스 ID */
        private UUID resourceId;

        /* 그룹 ID */
        private UUID groupId;

        /* 리소스 명 */
        private String resourceName;

        /* 리소스 URL */
        private String resourceUrl;

        /* 리소스 설명 */
        private String resourceDesc;

        /* 리소스 순서 */
        private Integer resourceOrder;

        /* 사용 여부 */
        private Boolean used;

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

}
