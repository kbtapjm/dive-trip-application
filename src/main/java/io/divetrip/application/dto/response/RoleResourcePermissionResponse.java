package io.divetrip.application.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.divetrip.library.domain.entity.enumeration.Permission;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

public class RoleResourcePermissionResponse {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class ResourcePermission {
        /* 롤 리소스 퍼미션 ID */
        private UUID roleResourcePermissionId;

        /* 권한 */
        private Permission permission;

        /* 등록일 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
        private LocalDateTime createdAt;
    }
}
