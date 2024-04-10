package io.divetrip.controller;

import io.divetrip.domain.entity.enumeration.Continent;
import io.divetrip.dto.request.DestinationRequest;
import io.divetrip.service.DestinationService;
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
public class DestinationController {

    private final DestinationService destinationService;

    @PostMapping(value = "/destinations", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createDestination(@Valid @RequestBody DestinationRequest.CreateDestination dto) {
        if (log.isDebugEnabled()) {
            log.debug("DestinationRequest.CreateDestination: {}", dto.toString());
        }

        String destinationId = destinationService.createDestination(dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{destinationId}")
                .buildAndExpand(destinationId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/destinations", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDestination(
            @RequestParam(value = "continent", required = false) Continent continent,
            @RequestParam(value = "area", required = false, defaultValue = "") String area,
            @RequestParam(value = "countryId", required = false) Long countryId
    ) {
        DestinationRequest.SearchDestination searchDto = DestinationRequest.SearchDestination.builder()
                .continent(continent)
                .area(area)
                .countryId(countryId)
                .build();

        return ResponseEntity.ok(destinationService.getDestinations(searchDto));
    }

    @GetMapping(value = "/destinations/{destinationId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDestination(@PathVariable UUID destinationId) {
        return ResponseEntity.ok(destinationService.getDestinationById(destinationId));
    }

    @PutMapping(value = "/destinations/{destinationId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDestination(@PathVariable UUID destinationId, @Valid @RequestBody DestinationRequest.UpdateDestination dto) {
        if (log.isDebugEnabled()) {
            log.debug("DestinationRequest.UpdateDestination: {}", dto.toString());
        }

        destinationService.updateDestination(destinationId, dto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/destinations/{destinationId}")
    public ResponseEntity<?> deleteDestination(@PathVariable UUID destinationId) {
        destinationService.deleteDestination(destinationId);

        return ResponseEntity.noContent().build();
    }

}
