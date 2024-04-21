package io.divetrip.service;

import io.divetrip.domain.entity.TripLodging;
import io.divetrip.domain.repository.TripLodgingRepository;
import io.divetrip.enumeration.DiveTripError;
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
