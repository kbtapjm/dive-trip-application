package io.divetrip.mapper;

import io.divetrip.domain.entity.Diver;
import io.divetrip.dto.request.DiverRequestDto;
import io.divetrip.mapper.common.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DiverMapper extends GenericMapper<DiverRequestDto.DiverCreate, Diver> {

}
