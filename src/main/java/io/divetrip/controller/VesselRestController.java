package io.divetrip.controller;

import io.divetrip.service.VesselService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class VesselRestController {

    private final VesselService vesselService;


}
