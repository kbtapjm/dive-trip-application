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

    @Transactional
    public String createDiver(DiverRequestDto.CreateDiver dto) {
        if (diverRepository.existsByEmail(dto.getEmail())) {
            throw DiveTripError.EMAIL_DUPLICATED.exception(dto.getEmail());
        }

        // TODO: 비밀번호 암호화 처리(스프링 시큐리티 적용후에)

        Diver diver = diverRepository.save(diverCreateRequestMapper.toEntity(dto));

        return diver.getDiverId().toString();
    }

    public List<?> getDiversAll() {
        // TODO: convert to resources dto
        return diverRepository.findAll().stream()
                .map(diverResponseMapper::toDto)
                .collect(Collectors.toList());
    }

    public DiverResponseDto getDiver(final UUID diverId) {
        Diver diver = diverRepository.findById(diverId)
                .orElseThrow(() ->  DiveTripError.DIVER_NOT_FOUND.exception(diverId.toString()));

        return diverResponseMapper.toDto(diver);
    }

    @Transactional
    public void updateDiver(final UUID diverId, DiverRequestDto.UpdateDiver dto) {
        Diver diver = diverRepository.findById(diverId)
                .orElseThrow(() ->  DiveTripError.DIVER_NOT_FOUND.exception(diverId.toString()));

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
        Diver diver = diverRepository.findById(diverId)
                .orElseThrow(() ->  DiveTripError.DIVER_NOT_FOUND.exception(diverId.toString()));

        diverRepository.deleteById(diverId);
    }

}

