package io.divetrip.application.mapper.request;

import io.divetrip.application.dto.request.RoleRequest;
import io.divetrip.application.mapper.GenericMapper;
import io.divetrip.library.domain.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleCreateRequestMapper extends GenericMapper<RoleRequest.CreateRole, Role> {
}
