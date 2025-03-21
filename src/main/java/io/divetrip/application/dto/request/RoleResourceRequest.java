package io.divetrip.application.dto.request;

import io.divetrip.library.domain.entity.enumeration.Permission;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

public class RoleResourceRequest {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    @ToString
    public static class CreateRoleResource {
        /* 역할 ID */
        @NotNull
        private UUID roleId;

        /* 리소스 ID */
        @NotNull
        private UUID resourceId;

        /* 권한 목록 */
        private List<Permission> permissions;
    }

}
