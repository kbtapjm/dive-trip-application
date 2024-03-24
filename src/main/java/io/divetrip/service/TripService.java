package io.divetrip.service;

import io.divetrip.domain.entity.Trip;
import io.divetrip.domain.repository.TripRepository;
import io.divetrip.dto.request.TripRequest;
import io.divetrip.mapper.request.TripCreateRequestMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepository;
    private final TripCreateRequestMapper tripCreateRequestMapper;

    public String createTrip(final TripRequest.CreateTrip dto) {
        /* create trip */
        Trip trip = tripRepository.save(tripCreateRequestMapper.toEntity(dto));


        return "";
    }

}
