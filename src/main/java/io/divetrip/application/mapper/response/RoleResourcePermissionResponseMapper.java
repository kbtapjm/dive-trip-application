package io.divetrip.application.mapper.response;

import io.divetrip.application.dto.response.RoleResourcePermissionResponse;
import io.divetrip.library.domain.entity.RoleResourcePermission;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleResourcePermissionResponseMapper {

    RoleResourcePermissionResponse.ResourcePermission toResourcePermission(final RoleResourcePermission roleResourcePermission);

}
