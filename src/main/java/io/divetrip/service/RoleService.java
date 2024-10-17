package io.divetrip.service;

import io.divetrip.domain.entity.Role;
import io.divetrip.domain.repository.RoleRepository;
import io.divetrip.dto.request.RoleRequest;
import io.divetrip.dto.response.RoleResponse;
import io.divetrip.enumeration.DiveTripError;
import io.divetrip.mapper.request.RoleCreateRequestMapper;
import io.divetrip.mapper.response.RoleResponseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    private final RoleCreateRequestMapper roleCreateRequestMapper;
    private final RoleResponseMapper roleResponseMapper;

    @Transactional
    @CachePut(cacheNames = "role", key = "#result.roleId")
    public RoleResponse.Role createRole(final RoleRequest.CreateRole dto) {
        if(roleRepository.existsByRoleCode(dto.getRoleCode())) {
            throw DiveTripError.ROLE_CODE_DUPLICATED.exception(dto.getRoleCode());
        }

        Role role = roleRepository.save(roleCreateRequestMapper.toEntity(dto));

        return roleResponseMapper.toRoleDto(role);
    }

    public List<RoleResponse.Roles> getRoles() {
        return roleRepository.findAll().stream()
                .map(roleResponseMapper::toRolesDto)
                .collect(Collectors.toList());
    }

    @Cacheable(cacheNames = "role", key = "#roleId")
    public RoleResponse.Role getRole(final UUID roleId) {
        return roleResponseMapper.toRoleDto(this.getRoleByRoleId(roleId));
    }

    @Transactional
    @CachePut(cacheNames = "role", key = "#result.roleId")
    public RoleResponse.Role updateRole(final UUID roleId, final RoleRequest.UpdateRole dto) {
        Role role = this.getRoleByRoleId(roleId);
        role.update(
                dto.getRoleName(),
                dto.getNote()
        );

        return roleResponseMapper.toRoleDto(role);
    }

    @Transactional
    @CacheEvict(cacheNames = "role", key = "#roleId")
    public void deleteRole(final UUID roleId) {
        Role role = this.getRoleByRoleId(roleId);

        roleRepository.delete(role);
    }

    public Role getRoleByRoleId(final UUID roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(() ->  DiveTripError.ROLE_NOT_FOUND.exception(roleId.toString()));
    }

}
