package io.divetrip.application.mapper.request;

import io.divetrip.application.dto.request.ResourceRequest;
import io.divetrip.library.domain.entity.Resource;
import io.divetrip.library.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ResourceCreateRequestMapper extends GenericMapper<ResourceRequest.CreateResource, Resource> {
}
