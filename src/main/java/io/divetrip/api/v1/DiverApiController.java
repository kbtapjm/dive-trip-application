package io.divetrip.api.v1;

import io.divetrip.dto.request.DiverRequestDto;
import io.divetrip.service.DiverService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class DiverApiController {

    private final DiverService diverService;

    @PostMapping(value = "/divers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createDiver(@Valid @RequestBody DiverRequestDto.CreateDiver dto) {
        String diverId = diverService.createDiver(dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{diverId}")
                .buildAndExpand(diverId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/divers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDivers() {
        return ResponseEntity.ok(diverService.getDiversAll());
    }

    @GetMapping(value = "/divers/{diverId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDiver(@PathVariable UUID diverId) {
        return ResponseEntity.ok(diverService.getDiver(diverId));
    }

    @PutMapping(value = "/divers/{diverId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDiver(@PathVariable UUID diverId, @Valid @RequestBody DiverRequestDto.UpdateDiver dto) {
        diverService.updateDiver(diverId, dto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/divers/{diverId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteDiver(@PathVariable UUID diverId) {
        diverService.deleteDiver(diverId);

        return ResponseEntity.noContent().build();
    }

}
