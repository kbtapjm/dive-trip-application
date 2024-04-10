package io.divetrip.service;

import io.divetrip.domain.entity.Vessel;
import io.divetrip.domain.entity.VesselCabin;
import io.divetrip.domain.repository.VesselCabinRepository;
import io.divetrip.dto.request.VesselCabinRequest;
import io.divetrip.dto.response.VesselCabinResponse;
import io.divetrip.enumeration.DiveTripError;
import io.divetrip.mapper.request.VesselCabinCreateRequestMapper;
import io.divetrip.mapper.response.VesselCabinResponseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class VesselCabinService {
    private final VesselCabinRepository vesselCabinRepository;
    private final VesselCabinResponseMapper vesselCabinResponseMapper;
    private final VesselCabinCreateRequestMapper vesselCabinCreateRequestMapper;
    private final VesselService vesselService;

    @Transactional
    public String createVesselCabin(final UUID vesselId, final VesselCabinRequest.CreateVesselCabin dto) {
        Vessel Vessel = vesselService.getVesselByVesselId(vesselId);

        if (vesselCabinRepository.existsByVesselAndCabinName(Vessel, dto.getCabinName())) {
            throw DiveTripError.VESSEL_CABIN_NAME_DUPLICATED.exception(dto.getCabinName());
        }

        VesselCabin vesselCabin = vesselCabinRepository.save(vesselCabinCreateRequestMapper.toEntity(dto, Vessel));

        return vesselCabin.getVesselCabinId().toString();
    }

    public List<VesselCabinResponse.VesselCabins> getVesselCabinsByVesselId(final UUID vesselId) {
        Vessel Vessel = vesselService.getVesselByVesselId(vesselId);

        return Vessel.getVesselCabins().stream()
                .map(vesselCabinResponseMapper::toVesselCabins)
                .collect(Collectors.toList());
    }

    public VesselCabinResponse.VesselCabin getVesselCabin(final UUID vesselId, final UUID vesselCabinId) {
        Vessel Vessel = vesselService.getVesselByVesselId(vesselId);

        return vesselCabinResponseMapper.toVesselCabin(this.getVesselCabinByVesselAndVesselCabinId(Vessel, vesselCabinId));
    }

    @Transactional
    public void updateVesselCabin(final UUID vesselId, final UUID vesselCabinId, final VesselCabinRequest.UpdateVesselCabin dto) {
        Vessel Vessel = vesselService.getVesselByVesselId(vesselId);

        this.getVesselCabinByVesselAndVesselCabinId(Vessel, vesselCabinId).update(
                dto.getCabinName(),
                dto.getDescription(),
                dto.getSize(),
                dto.getMaxOccupancy(),
                dto.getBedding(),
                dto.getEnsuiteBathroom(),
                dto.getAircon(),
                dto.getUsed()
        );
    }

    public void deleteVesselCabin(final UUID vesselId, final UUID vesselCabinId) {
        Vessel Vessel = vesselService.getVesselByVesselId(vesselId);

        vesselCabinRepository.delete(this.getVesselCabinByVesselAndVesselCabinId(Vessel, vesselCabinId));
    }

    private VesselCabin getVesselCabinByVesselAndVesselCabinId(Vessel Vessel, final UUID vesselCabinId) {
        return vesselCabinRepository.findByVesselAndVesselCabinId(Vessel, vesselCabinId)
                .orElseThrow(() ->  DiveTripError.VESSEL_CABIN_NOT_FOUND.exception(vesselCabinId.toString()));
    }

}
