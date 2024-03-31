package io.divetrip.controller;

import io.divetrip.dto.request.TripRequest;
import io.divetrip.service.TripService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class TripController {

    private final TripService tripService;

    @PostMapping(value = "/trips", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createVessel(@Valid @RequestBody TripRequest.CreateTrip dto) {
        if (log.isDebugEnabled()) {
            log.debug("TripRequest.CreateTrip: {}", dto.toString());
        }

        String tripId = tripService.createTrip(dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{tripId}")
                .buildAndExpand(tripId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

}
