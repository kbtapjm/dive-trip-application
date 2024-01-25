package io.divetrip.mapper;

import io.divetrip.domain.entity.Diver;
import io.divetrip.dto.request.DiverRequestDto;
import io.divetrip.mapper.common.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DiverCreateRequestMapper extends GenericMapper<DiverRequestDto.CreateDiver, Diver> {

    @Mapping(source = "password", target = "password")
    @Mapping(source = "diverCreate.email", target = "createdBy")
    Diver toEntity(DiverRequestDto.CreateDiver diverCreate, String password);
}
