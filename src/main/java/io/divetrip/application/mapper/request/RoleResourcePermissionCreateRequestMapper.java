package io.divetrip.application.mapper.request;

import io.divetrip.library.domain.entity.RoleResource;
import io.divetrip.library.domain.entity.RoleResourcePermission;
import io.divetrip.library.domain.entity.enumeration.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleResourcePermissionCreateRequestMapper  {

    @Mapping(source = "permission", target = "permission")
    @Mapping(source = "roleResource", target = "roleResource")
    @Mapping(target = "createdAt", ignore = true)
    RoleResourcePermission toEntity(Permission permission, RoleResource roleResource);

}
