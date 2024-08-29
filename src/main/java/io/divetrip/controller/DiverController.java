package io.divetrip.controller;

import io.divetrip.dto.PageDto;
import io.divetrip.dto.request.DiverRequest;
import io.divetrip.service.DiverService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class DiverController {
    private final DiverService diverService;

    @PostMapping(value = "/divers", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createDiver(@Valid @RequestBody DiverRequest.CreateDiver dto) {
        String diverId = diverService.createDiver(dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{diverId}")
                .buildAndExpand(diverId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/divers", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('MANAGE', 'ADMIN')")
    public ResponseEntity<?> getDivers(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value = "sort", required = false, defaultValue = "createdAt") String sort,
            @RequestParam(value = "orderBy", required = false, defaultValue = "desc") String orderBy,
            @RequestParam(value = "name", required = false, defaultValue = "") String name,
            @RequestParam(value = "gender", required = false, defaultValue = "") String gender
    ) {
        PageDto pageDto = PageDto.builder()
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();

        DiverRequest.SearchDiver searchDto = DiverRequest.SearchDiver.builder()
                .sort(sort)
                .orderBy(orderBy)
                .name(name)
                .gender(gender)
                .build();

        return ResponseEntity.ok(diverService.getDiversAll(pageDto, searchDto));
    }

    @GetMapping(value = "/divers/{diverId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDiver(@PathVariable UUID diverId) {
        return ResponseEntity.ok(diverService.getDiver(diverId));
    }

    @PutMapping(value = "/divers/{diverId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDiver(@PathVariable UUID diverId, @Valid @RequestBody DiverRequest.UpdateDiver dto) {
        diverService.updateDiver(diverId, dto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/divers/{diverId}")
    public ResponseEntity<?> deleteDiver(@PathVariable UUID diverId) {
        diverService.deleteDiver(diverId);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/divers/{diverId}/password", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDiverPassword(@PathVariable UUID diverId, @Valid @RequestBody DiverRequest.UpdatePassword dto) {
        diverService.updateDiverPassword(diverId, dto);

        return ResponseEntity.ok().build();
    }
}
