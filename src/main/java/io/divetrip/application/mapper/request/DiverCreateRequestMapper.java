package io.divetrip.application.mapper.request;

import io.divetrip.application.dto.request.DiverRequest;
import io.divetrip.application.mapper.GenericMapper;
import io.divetrip.library.domain.entity.Diver;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DiverCreateRequestMapper extends GenericMapper<DiverRequest.CreateDiver, Diver> {

    @Mapping(source = "password", target = "password")
    @Mapping(source = "diverCreate.email", target = "createdBy")
    Diver toEntity(DiverRequest.CreateDiver diverCreate, String password);
}
