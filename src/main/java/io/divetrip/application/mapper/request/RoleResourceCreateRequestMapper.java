package io.divetrip.application.mapper.request;

import io.divetrip.library.domain.entity.Resource;
import io.divetrip.library.domain.entity.Role;
import io.divetrip.library.domain.entity.RoleResource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleResourceCreateRequestMapper  {

    @Mapping(source = "role", target = "role")
    @Mapping(source = "resource", target = "resource")
    @Mapping(target = "createdAt", ignore = true)
    RoleResource toEntity(Role role, Resource resource);


}
