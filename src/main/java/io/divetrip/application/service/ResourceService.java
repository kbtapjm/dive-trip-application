package io.divetrip.application.service;

import io.divetrip.application.dto.request.ResourceRequest;
import io.divetrip.application.dto.response.ResourceResponse;
import io.divetrip.application.enumeration.DiveTripError;
import io.divetrip.application.mapper.request.ResourceCreateRequestMapper;
import io.divetrip.application.mapper.response.ResourceResponseMapper;
import io.divetrip.library.domain.entity.Resource;
import io.divetrip.library.domain.repository.ResourceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    public List<ResourceResponse.Resources> getResources() {
        return resourceRepository.findAll().stream()
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

        resourceRepository.deleteById(resourceId);
    }

    private Resource getResourceByResourceId(final UUID resourceId) {
        return resourceRepository.findById(resourceId)
                .orElseThrow(() ->  DiveTripError.RESOURCE_NOT_FOUND.exception(resourceId.toString()));
    }

}
