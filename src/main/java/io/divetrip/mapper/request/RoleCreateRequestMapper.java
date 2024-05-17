package io.divetrip.mapper.request;

import io.divetrip.domain.entity.Role;
import io.divetrip.dto.request.RoleRequest;
import io.divetrip.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleCreateRequestMapper extends GenericMapper<RoleRequest.CreateRole, Role> {
}
