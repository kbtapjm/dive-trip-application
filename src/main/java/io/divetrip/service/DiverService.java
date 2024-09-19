package io.divetrip.service;

import io.divetrip.domain.entity.Diver;
import io.divetrip.domain.entity.DiverRole;
import io.divetrip.domain.entity.Role;
import io.divetrip.domain.entity.enumeration.Gender;
import io.divetrip.domain.repository.DiverRepository;
import io.divetrip.dto.PageDto;
import io.divetrip.dto.request.DiverRequest;
import io.divetrip.dto.response.DiverResponse;
import io.divetrip.dto.response.RoleResponse;
import io.divetrip.enumeration.DiveTripError;
import io.divetrip.mapper.request.DiverCreateRequestMapper;
import io.divetrip.mapper.response.DiverResponseMapper;
import io.divetrip.mapper.response.RoleResponseMapper;
import io.divetrip.service.support.DiverSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DiverService {
    private final DiverRepository diverRepository;
    private final DiverCreateRequestMapper diverCreateRequestMapper;
    private final DiverResponseMapper diverResponseMapper;
    private final RoleResponseMapper roleResponseMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Transactional
    public String createDiver(DiverRequest.CreateDiver dto) {
        if (diverRepository.existsByEmail(dto.getEmail())) {
            throw DiveTripError.EMAIL_DUPLICATED.exception(dto.getEmail());
        }

        Diver diver = diverRepository.save(diverCreateRequestMapper.toEntity(dto, passwordEncoder.encode(dto.getPassword())));

        return diver.getDiverId().toString();
    }

    public DiverResponse.DiverList getDiversAll(PageDto pageDto, DiverRequest.SearchDiver searchDto) {
        PageRequest pageRequest = PageRequest.of(pageDto.getPageNumber(), pageDto.getPageSize(), searchDto.getPageSort());

        Page<Diver> page = diverRepository.findAll(new DiverSpecification(searchDto), pageRequest);
        pageDto.setPage(pageDto.getPageNumber(), pageDto.getPageSize(), page.getTotalElements(), page.getTotalPages());

        return DiverResponse.DiverList.builder()
                .content(page.getContent().stream().map(diverResponseMapper::toDiversDto).collect(Collectors.toList()))
                .page(pageDto)
                .search(searchDto)
                .build();
    }

    public DiverResponse.Diver getDiver(final UUID diverId) {
        Diver diver = this.getDiverByDiverId(diverId);

        return diverResponseMapper.toDiverDto(diver);
    }

    @Transactional
    public void updateDiver(final UUID diverId, DiverRequest.UpdateDiver dto) {
        Diver diver = this.getDiverByDiverId(diverId);

        diver.update(
                dto.getFamilyName(),
                dto.getGivenName(),
                Gender.findByValue(dto.getGender()),
                dto.getBirthday(),
                dto.getNationality(),
                dto.getCountryCode(),
                dto.getContactNumber(),
                dto.getPassportNo(),
                dto.getPassportExpiryDate(),
                dto.getLicensed(),
                dto.getAddress().getCity(),
                dto.getAddress().getStreet(),
                dto.getAddress().getZipCode()
        );
    }

    public void deleteDiver(final UUID diverId) {
        this.getDiverByDiverId(diverId);

        diverRepository.deleteById(diverId);
    }

    @Transactional
    public void updateDiverPassword(final UUID diverId, DiverRequest.UpdatePassword dto) {
        Diver diver = this.getDiverByDiverId(diverId);

        if (!passwordEncoder.matches(dto.getOldPassword(), diver.getPassword())) {
            throw DiveTripError.DIVER_PASSWORD_NOT_MATCH.exception();
        }
        if (StringUtils.equals(dto.getOldPassword(), dto.getNewPassword())) {
            throw DiveTripError.DIVER_PASSWORD_CAN_NOT_SAME.exception();
        }

        diver.changePassword(passwordEncoder.encode(dto.getNewPassword()));
    }

    public Diver getDiverByDiverId(final UUID diverId) {
        return diverRepository.findById(diverId)
                .orElseThrow(() ->  DiveTripError.DIVER_NOT_FOUND.exception(diverId.toString()));
    }

    @Transactional
    public void createDiverRole(final UUID diverId, DiverRequest.CreateDiverRole dto) {
        Diver diver = this.getDiverByDiverId(diverId);

        Role role = roleService.getRoleByRoleId(dto.getRoleId());

        if (diver.getDiverRoles().stream().anyMatch(item -> item.getRole() == role)) {
            throw DiveTripError.DIVER_ROLE_DUPLICATED.exception(dto.getRoleId().toString());
        }

        DiverRole diverRole = DiverRole.builder()
                .diver(diver)
                .role(role)
                .build();

        diver.addDiverRole(diverRole);
    }

    public List<RoleResponse.Roles> getDiverRoles(final UUID diverId) {
        Diver diver = this.getDiverByDiverId(diverId);

        return diver.getDiverRoles().stream()
                .map(m -> {
                    return roleResponseMapper.toRolesDto(m.getRole());
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteDiverRole(final UUID diverId, final UUID roleId) {
        Diver diver = this.getDiverByDiverId(diverId);

        Role role = roleService.getRoleByRoleId(roleId);
        if (diver.getDiverRoles().stream().noneMatch(item -> item.getRole() == role)) {
            throw DiveTripError.DIVER_ROLE_NOT_FOUND.exception(roleId.toString());
        }

        diver.removeDiverRole(role);
    }

}

