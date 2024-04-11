package io.divetrip.controller;

import io.divetrip.domain.entity.enumeration.TripStatus;
import io.divetrip.dto.PageDto;
import io.divetrip.dto.request.TripRequest;
import io.divetrip.service.TripService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class TripController {

    private final TripService tripService;

    @PostMapping(value = "/trips", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTrips(@Valid @RequestBody TripRequest.CreateTrip dto) {
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

    @GetMapping(value = "/trips", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTrips(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value = "sort", required = false, defaultValue = "createdAt") String sort,
            @RequestParam(value = "orderBy", required = false, defaultValue = "desc") String orderBy,
            @RequestParam(value = "tripStatus", required = false, defaultValue = "") TripStatus tripStatus,
            @RequestParam(value = "area", required = false, defaultValue = "") String area
    ) {
        PageDto pageDto = PageDto.builder()
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();

        TripRequest.SearchTrip searchDto = TripRequest.SearchTrip.builder()
                .sort(sort)
                .orderBy(orderBy)
                .tripStatus(tripStatus)
                .area(area)
                .build();

        return ResponseEntity.ok(tripService.getTrips(pageDto, searchDto));
    }

    @GetMapping(value = "/trips/{tripId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTrip(@PathVariable UUID tripId) {
        return ResponseEntity.ok(tripService.getTrip(tripId));
    }

}
