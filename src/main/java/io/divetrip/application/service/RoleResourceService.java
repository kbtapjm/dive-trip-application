package io.divetrip.application.service;

import io.divetrip.application.dto.request.RoleResourceRequest;
import io.divetrip.library.domain.entity.Resource;
import io.divetrip.library.domain.entity.Role;
import io.divetrip.library.domain.repository.RoleResourcePermissionRepository;
import io.divetrip.library.domain.repository.RoleResourceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleResourceService {
    private final RoleResourceRepository roleResourceRepository;
    private final RoleResourcePermissionRepository roleResourcePermissionRepository;
    private final RoleService roleService;
    private final ResourceService resourceService;

    public void createRoleResource(final RoleResourceRequest.CreateRoleResource dto) {
        /* get role by */
        Role role = roleService.getRoleByRoleId(dto.getRoleId());

        /* get resource by */
        Resource resource = resourceService.getResourceByResourceId(dto.getResourceId());


    }


}
