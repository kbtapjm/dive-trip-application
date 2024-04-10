package io.divetrip.controller;

import io.divetrip.domain.entity.enumeration.VesselStatus;
import io.divetrip.dto.PageDto;
import io.divetrip.dto.request.VesselCabinRequest;
import io.divetrip.dto.request.VesselRequest;
import io.divetrip.service.VesselCabinService;
import io.divetrip.service.VesselService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
public class VesselController {
    private final VesselService vesselService;
    private final VesselCabinService vesselCabinService;

    @PostMapping(value = "/vessels", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createVessel(@Valid @RequestBody VesselRequest.CreateVessel dto) {
        if (log.isDebugEnabled()) {
            log.debug("VesselRequest.CreateVessel: {}", dto.toString());
        }

        String vesselId = vesselService.createVessel(dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{vesselId}")
                .buildAndExpand(vesselId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/vessels", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDivers(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value = "sort", required = false, defaultValue = "createdAt") String sort,
            @RequestParam(value = "orderBy", required = false, defaultValue = "desc") String orderBy,
            @RequestParam(value = "vesselName", required = false, defaultValue = "") String vesselName,
            @RequestParam(value = "vesselStatus", required = false, defaultValue = "") VesselStatus vesselStatus,
            @RequestParam(value = "used", required = false) Boolean used
    ) {
        PageDto pageDto = PageDto.builder()
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();

        VesselRequest.SearchVessel searchDto = VesselRequest.SearchVessel.builder()
                .sort(sort)
                .orderBy(orderBy)
                .vesselName(vesselName)
                .vesselStatus(vesselStatus)
                .used(used)
                .build();

        return ResponseEntity.ok(vesselService.getVessels(pageDto, searchDto));
    }

    @GetMapping(value = "/vessels/{vesselId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getVessel(@PathVariable UUID vesselId) {
        return ResponseEntity.ok(vesselService.getVessel(vesselId));
    }

    @PutMapping(value = "/vessels/{vesselId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateVessel(@PathVariable UUID vesselId, @Valid @RequestBody VesselRequest.UpdateVessel dto) {
        if (log.isDebugEnabled()) {
            log.debug("VesselRequest.UpdateVessel: {}", dto.toString());
        }

        vesselService.updateVessel(vesselId, dto);

        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/vessels/{vesselId}/used", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateVesselUsed(@PathVariable UUID vesselId, @Valid @RequestBody VesselRequest.changeVesselUsed dto) {
        if (log.isDebugEnabled()) {
            log.debug("VesselRequest.changeVesselUsed: {}", dto.toString());
        }

        vesselService.updateVesselUsed(vesselId, dto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/vessels/{vesselId}")
    public ResponseEntity<?> deleteVessel(@PathVariable UUID vesselId) {
        vesselService.deleteVessel(vesselId);

        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/vessels/{vesselId}/cabins", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createVesselCabin(@PathVariable UUID vesselId, @Valid @RequestBody VesselCabinRequest.CreateVesselCabin dto) {
        if (log.isDebugEnabled()) {
            log.debug("VesselCabinRequest.CreateVesselCabin: {}", dto.toString());
        }

        String vesselCabinId = vesselCabinService.createVesselCabin(vesselId, dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{vesselCabinId}")
                .buildAndExpand(vesselCabinId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/vessels/{vesselId}/cabins", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getVesselCabinsByVesselId(@PathVariable UUID vesselId) {
        return ResponseEntity.ok(vesselCabinService.getVesselCabinsByVesselId(vesselId));
    }

    @GetMapping(value = "/vessels/{vesselId}/cabins/{vesselCabinId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getVesselCabin(@PathVariable UUID vesselId, @PathVariable UUID vesselCabinId) {
        return ResponseEntity.ok(vesselCabinService.getVesselCabin(vesselId, vesselCabinId));
    }

    @PutMapping(value = "/vessels/{vesselId}/cabins/{vesselCabinId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createVesselCabin(
            @PathVariable UUID vesselId,
            @PathVariable UUID vesselCabinId,
            @Valid @RequestBody VesselCabinRequest.UpdateVesselCabin dto) {
        if (log.isDebugEnabled()) {
            log.debug("VesselCabinRequest.UpdateVesselCabin: {}", dto.toString());
        }

        vesselCabinService.updateVesselCabin(vesselId, vesselCabinId, dto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/vessels/{vesselId}/cabins/{vesselCabinId}")
    public ResponseEntity<?> deleteVessel(@PathVariable UUID vesselId, @PathVariable UUID vesselCabinId) {
        vesselCabinService.deleteVesselCabin(vesselId, vesselCabinId);

        return ResponseEntity.noContent().build();
    }
}
