package io.divetrip.controller;

import io.divetrip.dto.request.RoleRequest;
import io.divetrip.dto.response.RoleResponse;
import io.divetrip.service.RoleService;
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

        RoleResponse.Role role = roleService.updateRole(roleId, dto);

        return ResponseEntity.ok(role);
    }

    @DeleteMapping(value = "/roles/{roleId}")
    public ResponseEntity<?> deleteVessel(@PathVariable UUID roleId) {
        roleService.deleteRole(roleId);

        return ResponseEntity.noContent().build();
    }

}
