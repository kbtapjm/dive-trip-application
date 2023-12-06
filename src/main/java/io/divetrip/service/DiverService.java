package io.divetrip.service;

import io.divetrip.domain.entity.Diver;
import io.divetrip.domain.entity.enumeration.Gender;
import io.divetrip.domain.repository.DiverRepository;
import io.divetrip.dto.request.DiverRequestDto;
import io.divetrip.dto.response.DiverResponseDto;
import io.divetrip.enumeration.DiveTripError;
import io.divetrip.mapper.DiverCreateRequestMapper;
import io.divetrip.mapper.DiverResponseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public String createDiver(DiverRequestDto.CreateDiver dto) {
        if (diverRepository.existsByEmail(dto.getEmail())) {
            throw DiveTripError.EMAIL_DUPLICATED.exception(dto.getEmail());
        }

        Diver diver = diverRepository.save(diverCreateRequestMapper.toEntity(dto, passwordEncoder.encode(dto.getPassword())));

        return diver.getDiverId().toString();
    }

    public List<DiverResponseDto.Divers> getDiversAll() {
        return diverRepository.findAll().stream()
                .map(diverResponseMapper::toDiversDto)
                .collect(Collectors.toList());
    }

    public DiverResponseDto.Diver getDiver(final UUID diverId) {
        Diver diver = this.getDiverByDiverId(diverId);

        return diverResponseMapper.toDiverDto(diver);
    }

    @Transactional
    public void updateDiver(final UUID diverId, DiverRequestDto.UpdateDiver dto) {
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
                dto.getLicensed()
        );
    }

    public void deleteDiver(final UUID diverId) {
        this.getDiverByDiverId(diverId);

        diverRepository.deleteById(diverId);
    }

    @Transactional
    public void updateDiverPassword(final UUID diverId, DiverRequestDto.UpdatePassword dto) {
        Diver diver = this.getDiverByDiverId(diverId);

        if (!passwordEncoder.matches(dto.getOldPassword(), diver.getPassword())) {
            throw DiveTripError.DIVER_PASSWORD_NOT_MATCH.exception();
        }
        if (StringUtils.equals(dto.getOldPassword(), dto.getNewPassword())) {
            throw DiveTripError.DIVER_PASSWORD_CAN_NOT_SAME.exception();
        }

        diver.changePassword(passwordEncoder.encode(dto.getNewPassword()));
    }

    private Diver getDiverByDiverId(final UUID diverId) {
        return diverRepository.findById(diverId)
                .orElseThrow(() ->  DiveTripError.DIVER_NOT_FOUND.exception(diverId.toString()));
    }

}

