package io.divetrip.api.v1;

import io.divetrip.dto.request.DiverRequestDto;
import io.divetrip.service.DiverService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class DiverApiController {

    private final DiverService diverService;

    @PostMapping(value = "/divers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createDiver(@Valid @RequestBody DiverRequestDto.DiverCreate dto) {

        String diverId = diverService.createDiver(dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{diverId}")
                .buildAndExpand(diverId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

}
