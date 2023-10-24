package io.divetrip.mapper;

import io.divetrip.domain.entity.Diver;
import io.divetrip.dto.request.DiverRequestDto;
import io.divetrip.mapper.common.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * componentModel: 스프링과 mapstruct 을 사용할 경우
 * unmappedTargetPolicy=ReportingPolicy.IGNORE: 일치하지 않는 필드를 무시
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DiverMapper extends GenericMapper<DiverRequestDto.DiverCreate, Diver> {

    @Override
    @Mapping(target = "createdBy", source = "email")
    Diver toEntity(DiverRequestDto.DiverCreate diverCreate);
}
