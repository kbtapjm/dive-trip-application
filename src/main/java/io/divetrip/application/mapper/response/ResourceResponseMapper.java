package io.divetrip.application.mapper.response;

import io.divetrip.application.dto.response.ResourceResponse;
import io.divetrip.library.domain.entity.Resource;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ResourceResponseMapper {

    ResourceResponse.Resources toResources(final Resource resource);

    ResourceResponse.Resource toResource(final Resource resource);

}
