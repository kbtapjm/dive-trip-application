package io.divetrip.service;

import io.divetrip.domain.entity.Diver;
import io.divetrip.domain.entity.DiverRole;
import io.divetrip.domain.entity.Role;
import io.divetrip.domain.repository.DiverRepository;
import io.divetrip.domain.repository.RoleRepository;
import io.divetrip.dto.request.AuthRequest;
import io.divetrip.enumeration.DiveTripError;
import io.divetrip.mapper.request.AuthRequestMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final DiverRepository diverRepository;
    private final RoleRepository roleRepository;
    private final AuthRequestMapper authRequestMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public String signup(final AuthRequest.Signup dto) {
        if (diverRepository.existsByEmail(dto.getEmail())) {
            throw DiveTripError.EMAIL_DUPLICATED.exception(dto.getEmail());
        }

        /* get role */
        Role role = roleRepository.findByRoleCode("ROLE_DIVER");

        /* set diver */
        Diver diver = authRequestMapper.toEntity(dto, passwordEncoder.encode(dto.getPassword()));

        DiverRole diverRole = DiverRole.builder()
                .diver(diver)
                .role(role)
                .build();

        // TODO: 최초 가입자는 ROLE 할당을 하지 않고 email 인증을 했을 경우에 롤을 할당하는걸로 검토 필요
        diver.addDiverRoles(List.of(diverRole));

        Diver result = diverRepository.save(diver);

        return result.getDiverId().toString();
    }

}
