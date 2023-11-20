package io.divetrip.service;

import io.divetrip.domain.entity.Diver;
import io.divetrip.domain.repository.DiverRepository;
import io.divetrip.dto.request.DiverRequestDto;
import io.divetrip.enumeration.DiveTripError;
import io.divetrip.mapper.DiverCreateRequestMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DiverService {

    private final DiverRepository diverRepository;
    private final DiverCreateRequestMapper diverCreateRequestMapper;

    public String createDiver(DiverRequestDto.CreateDiver dto) {
        if (diverRepository.existsByEmail(dto.getEmail())) {
            throw DiveTripError.EMAIL_DUPLICATED.exception(dto.getEmail());
        }

        Diver diver = diverRepository.save(diverCreateRequestMapper.toEntity(dto));

        return diver.getDiverId().toString();
    }

    public List<?> getDiversAll() {
        return diverRepository.findAll();
    }

}

