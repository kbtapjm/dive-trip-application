package io.divetrip.application.mapper.response;

import io.divetrip.application.dto.response.ResourceResponse;
import io.divetrip.library.domain.entity.Resource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ResourceResponseMapper {

    @Mapping(source = "subResources", target = "subResources")
    ResourceResponse.Resources toResources(final Resource resource, List<ResourceResponse.Resource> subResources);

    ResourceResponse.Resource toResource(final Resource resource);

}
