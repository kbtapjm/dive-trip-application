package io.divetrip.application.service;

import io.divetrip.application.dto.request.RoleRequest;
import io.divetrip.application.dto.request.RoleResourceRequest;
import io.divetrip.application.dto.response.RoleResponse;
import io.divetrip.application.enumeration.DiveTripError;
import io.divetrip.application.mapper.request.RoleCreateRequestMapper;
import io.divetrip.application.mapper.request.RoleResourceCreateRequestMapper;
import io.divetrip.application.mapper.request.RoleResourcePermissionCreateRequestMapper;
import io.divetrip.application.mapper.response.RoleResponseMapper;
import io.divetrip.library.domain.entity.Resource;
import io.divetrip.library.domain.entity.Role;
import io.divetrip.library.domain.entity.RoleResource;
import io.divetrip.library.domain.repository.RoleRepository;
import io.divetrip.library.domain.repository.RoleResourceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleResourceRepository roleResourceRepository;
    private final RoleCreateRequestMapper roleCreateRequestMapper;
    private final RoleResponseMapper roleResponseMapper;
    private final RoleResourceCreateRequestMapper roleResourceCreateRequestMapper;
    private final RoleResourcePermissionCreateRequestMapper roleResourcePermissionCreateRequestMapper;
    private final ResourceService resourceService;

    @Transactional
    @CacheEvict(cacheNames = "roles", key = "'all'")
    public RoleResponse.Role createRole(final RoleRequest.CreateRole dto) {
        if(roleRepository.existsByRoleCode(dto.getRoleCode())) {
            throw DiveTripError.ROLE_CODE_DUPLICATED.exception(dto.getRoleCode());
        }

        Role role = roleRepository.save(roleCreateRequestMapper.toEntity(dto));

        return roleResponseMapper.toRoleDto(role);
    }

    @Cacheable(cacheNames = "roles", key = "'all'")
    public List<RoleResponse.Roles> getRoles() {
        return roleRepository.findAll().stream()
                .map(roleResponseMapper::toRolesDto)
                .collect(Collectors.toList());
    }

    @Cacheable(cacheNames = "roles", key = "#roleId")
    public RoleResponse.Role getRole(final UUID roleId) {
        return roleResponseMapper.toRoleDto(this.getRoleByRoleId(roleId));
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "roles", key = "#roleId"),
            @CacheEvict(value = "roles", key = "'all'")
    })
    public void updateRole(final UUID roleId, final RoleRequest.UpdateRole dto) {
        Role role = this.getRoleByRoleId(roleId);
        role.update(
                dto.getRoleName(),
                dto.getNote()
        );
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "roles", key = "#roleId"),
            @CacheEvict(value = "roles", key = "'all'")
    })
    public void deleteRole(final UUID roleId) {
        Role role = this.getRoleByRoleId(roleId);

        roleRepository.delete(role);
    }

    @Transactional
    public String createRoleResource(final UUID roleId, final RoleResourceRequest.CreateRoleResource dto) {
        /* get role by */
        Role role = this.getRoleByRoleId(roleId);

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

    public Role getRoleByRoleId(final UUID roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(() ->  DiveTripError.ROLE_NOT_FOUND.exception(roleId.toString()));
    }

}
