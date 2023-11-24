package io.divetrip.mapper;

import io.divetrip.domain.entity.Diver;
import io.divetrip.dto.response.DiverResponseDto;
import io.divetrip.mapper.common.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DiverResponseMapper extends GenericMapper<DiverResponseDto, Diver> {
}
