package io.divetrip.application.controller;

import io.divetrip.application.dto.request.ResourceRequest;
import io.divetrip.application.dto.response.ResourceResponse;
import io.divetrip.application.service.ResourceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class ResourceController {
    private final ResourceService resourceService;

    @PostMapping(value = "/resources", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createResource(@Valid @RequestBody ResourceRequest.CreateResource dto) {
        if (log.isDebugEnabled()) {
            log.debug("ResourceRequest.CreateResource: {}", dto.toString());
        }

        ResourceResponse.Resource resource = resourceService.createResource(dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{resourceId}")
                .buildAndExpand(resource.getResourceId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/resources", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getResources() {
        return ResponseEntity.ok(resourceService.getResources());
    }

    @GetMapping(value = "/resources/{resourceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getResource(@PathVariable UUID resourceId) {
        return ResponseEntity.ok(resourceService.getResource(resourceId));
    }

    @PutMapping(value = "/resources/{resourceId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateResource(@PathVariable UUID resourceId, @Valid @RequestBody ResourceRequest.UpdateResource dto) {
        if (log.isDebugEnabled()) {
            log.debug("ResourceRequest.UpdateResource: {}", dto.toString());
        }

        resourceService.updateResource(resourceId, dto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/resources/{resourceId}")
    public ResponseEntity<?> deleteResource(@PathVariable UUID resourceId) {
        resourceService.deleteResource(resourceId);

        return ResponseEntity.noContent().build();
    }

}
