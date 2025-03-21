package io.divetrip.application.controller;

import io.divetrip.application.dto.request.RoleResourceRequest;
import io.divetrip.application.service.RoleResourceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class RoleResourceController {
    private final RoleResourceService roleResourceService;

    @PostMapping(value = "/role-resources", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createRoleResource(@Valid @RequestBody RoleResourceRequest.CreateRoleResource dto) {
        if (log.isDebugEnabled()) {
            log.debug("RoleResourceRequest.CreateRoleResource: {}", dto.toString());
        }

        String roleResourceId = roleResourceService.createRoleResource(dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{roleResourceId}")
                .buildAndExpand(roleResourceId)
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
