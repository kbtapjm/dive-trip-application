package io.divetrip.mapper.request;

import io.divetrip.domain.entity.Diver;
import io.divetrip.dto.request.DiverRequest;
import io.divetrip.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DiverCreateRequestMapper extends GenericMapper<DiverRequest.CreateDiver, Diver> {

    @Mapping(source = "password", target = "password")
    @Mapping(source = "diverCreate.email", target = "createdBy")
    Diver toEntity(DiverRequest.CreateDiver diverCreate, String password);
}
