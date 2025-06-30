package io.divetrip.application.mapper.response;

import io.divetrip.application.dto.response.RoleResourcePermissionResponse;
import io.divetrip.application.dto.response.RoleResourceResponse;
import io.divetrip.library.domain.entity.Resource;
import io.divetrip.library.domain.entity.RoleResource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleResourceResponseMapper {

    @Mapping(source = "roleResource.roleResourceId", target = "roleResourceId")
    @Mapping(source = "resource", target = "resource")
    @Mapping(source = "permissions", target = "permissions")
    @Mapping(source = "roleResource.createdAt", target = "createdAt")
    RoleResourceResponse.RoleResources toRoleResources(
            final Resource resource,
            final List<RoleResourcePermissionResponse.ResourcePermission> permissions,
            final RoleResource roleResource
    );
}
