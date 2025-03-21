package io.divetrip.application.service;

import io.divetrip.application.dto.request.RoleResourceRequest;
import io.divetrip.application.mapper.request.RoleResourceCreateRequestMapper;
import io.divetrip.application.mapper.request.RoleResourcePermissionCreateRequestMapper;
import io.divetrip.library.domain.entity.Resource;
import io.divetrip.library.domain.entity.Role;
import io.divetrip.library.domain.entity.RoleResource;
import io.divetrip.library.domain.repository.RoleResourcePermissionRepository;
import io.divetrip.library.domain.repository.RoleResourceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleResourceService {
    private final RoleResourceRepository roleResourceRepository;
    private final RoleResourcePermissionRepository roleResourcePermissionRepository;
    private final RoleService roleService;
    private final ResourceService resourceService;
    private final RoleResourceCreateRequestMapper roleResourceCreateRequestMapper;
    private final RoleResourcePermissionCreateRequestMapper roleResourcePermissionCreateRequestMapper;

    @Transactional
    public String createRoleResource(final RoleResourceRequest.CreateRoleResource dto) {
        /* get role by */
        Role role = roleService.getRoleByRoleId(dto.getRoleId());

        /* get resource by */
        Resource resource = resourceService.getResourceByResourceId(dto.getResourceId());

        /* set role resource */
        RoleResource roleResource = roleResourceRepository.save(roleResourceCreateRequestMapper.toEntity(role, resource));

        /* set role resource permission */
        roleResource.addAllPermissions(
                dto.getPermissions().stream()
                        .map(permission -> {
                            return roleResourcePermissionCreateRequestMapper.toEntity(permission, roleResource);
                        })
                        .collect(Collectors.toList())
        );

        return roleResource.getRoleResourceId().toString();
    }


}
