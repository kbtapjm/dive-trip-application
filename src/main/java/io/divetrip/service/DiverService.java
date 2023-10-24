package io.divetrip.service;

import io.divetrip.domain.entity.Diver;
import io.divetrip.domain.repository.DiverRepository;
import io.divetrip.dto.request.DiverRequestDto;
import io.divetrip.mapper.DiverMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DiverService {

    private final DiverRepository diverRepository;
    private final DiverMapper diverMapper;

    /**
     * 다이버 등록
     * @param dto
     * @return
     */
    public String createDiver(DiverRequestDto.DiverCreate dto) {
        if (diverRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("이미 등록된 e-mail 입니다.");
        }

        Diver diver = diverRepository.save(diverMapper.toEntity(dto));

        return diver.getDiverId().toString();
    }



}

