package io.divetrip.application.service;

import io.divetrip.application.enumeration.DiveTripError;
import io.divetrip.library.domain.entity.TripLodging;
import io.divetrip.library.domain.repository.TripLodgingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TripLodgingService {

    private final TripLodgingRepository tripLodgingRepository;

    public TripLodging getTripLodgingById(final UUID tripLodgingId) {
        return tripLodgingRepository.findById(tripLodgingId)
                .orElseThrow(() ->  DiveTripError.TRIP_LODGING_NOT_FOUND.exception(tripLodgingId.toString()));
    }


}
