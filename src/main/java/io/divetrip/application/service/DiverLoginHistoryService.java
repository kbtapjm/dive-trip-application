package io.divetrip.application.service;

import io.divetrip.library.domain.entity.DiverLoginHistory;
import io.divetrip.library.domain.repository.DiverLoginHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DiverLoginHistoryService {

    private final DiverLoginHistoryRepository diverLoginHistoryRepository;

    @Transactional
    public void createDiverLoginHistory(DiverLoginHistory diverLoginHistory) {
        diverLoginHistoryRepository.save(diverLoginHistory);
    }

}
