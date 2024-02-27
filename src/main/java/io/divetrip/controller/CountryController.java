package io.divetrip.controller;

import io.divetrip.service.CountryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class CountryController {

    private final CountryService countryService;

    @GetMapping(value = "/country/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCountryAll() {
        return ResponseEntity.ok(countryService.getCountryAll());
    }

}
