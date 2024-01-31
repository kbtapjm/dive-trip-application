package io.divetrip.service;

import io.divetrip.domain.repository.VesselRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class VesselService {

    private final VesselRepository vesselRepository;

}
