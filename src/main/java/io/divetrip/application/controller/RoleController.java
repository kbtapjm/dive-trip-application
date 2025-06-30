package io.divetrip.application.controller;

import io.divetrip.application.dto.request.RoleRequest;
import io.divetrip.application.dto.request.RoleResourceRequest;
import io.divetrip.application.dto.response.RoleResponse;
import io.divetrip.application.service.RoleService;
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
public class RoleController {
    private final RoleService roleService;

    @PostMapping(value = "/roles", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createRole(@Valid @RequestBody RoleRequest.CreateRole dto) {
        if (log.isDebugEnabled()) {
            log.debug("RoleRequest.CreateRole: {}", dto.toString());
        }

        RoleResponse.Role role = roleService.createRole(dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{roleId}")
                .buildAndExpand(role.getRoleId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/roles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRoles() {
        return ResponseEntity.ok(roleService.getRoles());
    }

    @GetMapping(value = "/roles/{roleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRole(@PathVariable UUID roleId) {
        return ResponseEntity.ok(roleService.getRole(roleId));
    }

    @PutMapping(value = "/roles/{roleId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateRole(@PathVariable UUID roleId, @Valid @RequestBody RoleRequest.UpdateRole dto) {
        if (log.isDebugEnabled()) {
            log.debug("RoleRequest.UpdateRole: {}", dto.toString());
        }

        roleService.updateRole(roleId, dto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/roles/{roleId}")
    public ResponseEntity<?> deleteRole(@PathVariable UUID roleId) {
        roleService.deleteRole(roleId);

        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/roles/{roleId}/resources", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createRoleResource(@PathVariable UUID roleId, @Valid @RequestBody RoleResourceRequest.CreateRoleResource dto) {
        if (log.isDebugEnabled()) {
            log.debug("RoleResourceRequest.CreateRoleResource: {}", dto.toString());
        }

        String roleResourceId = roleService.createRoleResource(roleId, dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{roleResourceId}")
                .buildAndExpand(roleResourceId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/roles/{roleId}/resources", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRoleResource(@PathVariable UUID roleId) {
        return ResponseEntity.ok(roleService.getRoleResource(roleId));
    }

}
