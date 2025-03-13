package io.divetrip.application.mapper.request;

import io.divetrip.application.dto.request.AuthRequest;
import io.divetrip.library.domain.entity.Diver;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthRequestMapper {

    @Mapping(source = "password", target = "password")
    @Mapping(source = "dto.email", target = "createdBy")
    Diver toEntity(final AuthRequest.Signup dto, String password);
}
