package io.divetrip.service;

import io.divetrip.domain.entity.Diver;
import io.divetrip.domain.repository.DiverRepository;
import io.divetrip.dto.request.DiverRequestDto;
import io.divetrip.enumeration.DiveTripError;
import io.divetrip.mapper.DiverMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DiverService {

    private final DiverRepository diverRepository;
    private final DiverMapper diverMapper;

    public String createDiver(DiverRequestDto.DiverCreate dto) {
        if (diverRepository.existsByEmail(dto.getEmail())) {
            throw DiveTripError.EMAIL_DUPLICATED.exception(dto.getEmail());
        }

        Diver diver = diverRepository.save(diverMapper.toEntity(dto));

        return diver.getDiverId().toString();
    }

    public List<?> getDiversAll() {
        return null;
    }

}

