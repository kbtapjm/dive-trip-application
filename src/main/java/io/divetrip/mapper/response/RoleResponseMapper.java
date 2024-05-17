package io.divetrip.mapper.response;

import io.divetrip.domain.entity.Role;
import io.divetrip.dto.response.RoleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleResponseMapper {

    RoleResponse.Roles toRolesDto(final Role role);

    RoleResponse.Role toRoleDto(final Role role);

}
