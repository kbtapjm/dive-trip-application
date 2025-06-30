package io.divetrip.application.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class RoleResourceResponse {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class RoleResources {
        /* 역할 리소스 ID */
        private UUID roleResourceId;

        /* 리소스  */
        private ResourceResponse.Resource resource;

        /* 퍼미션  */
        @Builder.Default
        private List<RoleResourcePermissionResponse.ResourcePermission> permissions = List.of();

        /* 등록 시간 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
        private LocalDateTime createdAt;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class RoleResource {
        /* 리소스  */
        private ResourceResponse.Resource resource;

        /* 퍼미션  */
        @Builder.Default
        private List<RoleResourcePermissionResponse.ResourcePermission> permissions = List.of();
    }

}
