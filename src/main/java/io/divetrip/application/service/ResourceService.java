package io.divetrip.application.service;

import io.divetrip.application.dto.request.ResourceRequest;
import io.divetrip.application.dto.response.ResourceResponse;
import io.divetrip.application.enumeration.DiveTripError;
import io.divetrip.application.mapper.request.ResourceCreateRequestMapper;
import io.divetrip.application.mapper.response.ResourceResponseMapper;
import io.divetrip.application.service.support.ResourceSpecification;
import io.divetrip.library.domain.entity.Resource;
import io.divetrip.library.domain.repository.ResourceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResourceService {

    private final ResourceRepository resourceRepository;
    private final ResourceCreateRequestMapper resourceCreateRequestMapper;
    private final ResourceResponseMapper resourceResponseMapper;

    @Transactional
    public ResourceResponse.Resource createResource(final ResourceRequest.CreateResource dto) {
        if (resourceRepository.existsByResourceUrl(dto.getResourceUrl())) {
            throw DiveTripError.RESOURCE_URL_DUPLICATED.exception(dto.getResourceUrl());
        }

        Resource resource = resourceRepository.save(resourceCreateRequestMapper.toEntity(dto));

        return resourceResponseMapper.toResource(resource);
    }

    public List<ResourceResponse.Resources> getResources(ResourceRequest.SearchResource searchDto) {
        return resourceRepository.findAll(new ResourceSpecification(searchDto), Sort.by(Sort.Direction.ASC, "resourceOrder"))
                .stream()
                .map(resourceResponseMapper::toResources)
                .collect(Collectors.toList());
    }

    public ResourceResponse.Resource getResource(final UUID resourceId) {
        return resourceResponseMapper.toResource(this.getResourceByResourceId(resourceId));
    }

    @Transactional
    public void updateResource(final UUID resourceId, final ResourceRequest.UpdateResource dto) {
        Resource resource = this.getResourceByResourceId(resourceId);
        resource.update(
                dto.getResourceName(),
                dto.getResourceUrl(),
                dto.getResourceDesc(),
                dto.getResourceOrder()
        );
    }

    @Transactional
    public void deleteResource(final UUID resourceId) {
        Resource resource = this.getResourceByResourceId(resourceId);

        List<Resource> resources = resourceRepository.findByGroupId(resource.getGroupId());
        if (!resources.isEmpty()) {
            throw DiveTripError.RESOURCE_CAN_NOT_DELETED.exception();
        }

        resourceRepository.deleteById(resourceId);
    }

    @Transactional
    public void updateResourceUsed(final UUID resourceId, final ResourceRequest.UpdateUsed dto) {
        Resource resource = this.getResourceByResourceId(resourceId);

        resource.updateUsed(dto.getUsed());
    }

    private Resource getResourceByResourceId(final UUID resourceId) {
        return resourceRepository.findById(resourceId)
                .orElseThrow(() ->  DiveTripError.RESOURCE_NOT_FOUND.exception(resourceId.toString()));
    }

}
