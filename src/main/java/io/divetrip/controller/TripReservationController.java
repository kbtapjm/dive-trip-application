package io.divetrip.controller;

import io.divetrip.domain.entity.enumeration.ReservationStatus;
import io.divetrip.dto.PageDto;
import io.divetrip.dto.request.TripReservationRequest;
import io.divetrip.service.TripReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
public class TripReservationController {

    private final TripReservationService tripReservationService;

    @PostMapping(value = "/trip-reservations", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTripReservation(@Valid @RequestBody TripReservationRequest.CreateTripReservation dto) {
        if (log.isDebugEnabled()) {
            log.debug("TripReservationRequest.CreateTripReservation: {}", dto.toString());
        }

        String tripReservationId = tripReservationService.createTripReservation(dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{tripReservationId}")
                .buildAndExpand(tripReservationId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/trip-reservations", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTripReservations(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value = "sort", required = false, defaultValue = "createdAt") String sort,
            @RequestParam(value = "orderBy", required = false, defaultValue = "desc") String orderBy,
            @RequestParam(value = "reservationStatus", required = false, defaultValue = "") ReservationStatus reservationStatus
    ) {
        PageDto pageDto = PageDto.builder()
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();

        TripReservationRequest.SearchTripReservation searchDto = TripReservationRequest.SearchTripReservation.builder()
                .sort(sort)
                .orderBy(orderBy)
                .reservationStatus(reservationStatus)
                .build();

        return ResponseEntity.ok(tripReservationService.getTripReservations(pageDto, searchDto));
    }

    @GetMapping(value = "/trip-reservations/{tripReservationId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTripReservation(@PathVariable UUID tripReservationId) {
        return ResponseEntity.ok(tripReservationService.getTripReservation(tripReservationId));
    }

    @PutMapping(value = "/trip-reservations/{tripReservationId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateTripReservation(@PathVariable UUID tripReservationId, @Valid @RequestBody TripReservationRequest.updateTripReservation dto) {
        if (log.isDebugEnabled()) {
            log.debug("TripReservationRequest.updateTripReservation dto: {}", dto.toString());
        }

        tripReservationService.updateTripReservation(tripReservationId, dto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/trip-reservations/{tripReservationId}")
    public ResponseEntity<?> deleteTripReservation(@PathVariable UUID tripReservationId) {
        tripReservationService.deleteTripReservation(tripReservationId);

        return ResponseEntity.noContent().build();
    }

}
