package io.divetrip.application.mapper.response;

import io.divetrip.application.dto.response.RoleResponse;
import io.divetrip.library.domain.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleResponseMapper {

    RoleResponse.Roles toRolesDto(final Role role);

    RoleResponse.Role toRoleDto(final Role role);

}
