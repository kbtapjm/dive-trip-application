package io.divetrip.service;

import io.divetrip.domain.entity.Vessel;
import io.divetrip.domain.repository.VesselRepository;
import io.divetrip.domain.repository.dto.request.VesselQueryRequest;
import io.divetrip.domain.repository.dto.response.VesselQueryResponse;
import io.divetrip.dto.PageDto;
import io.divetrip.dto.request.VesselRequest;
import io.divetrip.dto.response.VesselResponse;
import io.divetrip.enumeration.DiveTripError;
import io.divetrip.mapper.VesselCreateRequestMapper;
import io.divetrip.mapper.VesselResponseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class VesselService {

    private final VesselRepository vesselRepository;
    private final VesselCreateRequestMapper vesselCreateRequestMapper;
    private final VesselResponseMapper vesselResponseMapper;

    @Transactional
    public String createVessel(final VesselRequest.CreateVessel dto) {
        if (vesselRepository.existsByVesselName(dto.getVesselName())) {
            throw DiveTripError.VESSEL_NAME_DUPLICATED.exception(dto.getVesselName());
        }

        Vessel vessel = vesselRepository.save(vesselCreateRequestMapper.toEntity(dto));

        return vessel.getVesselId().toString();
    }

    public VesselResponse.VesselResult getVessels(PageDto pageDto, VesselRequest.SearchVessel searchDto) {
        PageRequest pageRequest = PageRequest.of(pageDto.getPageNumber(), pageDto.getPageSize(), searchDto.getPageSort());
        VesselQueryRequest vesselQueryRequest = VesselQueryRequest.builder()
                .vesselName(searchDto.getVesselName())
                .vesselStatus(searchDto.getVesselStatus())
                .used(searchDto.getUsed())
                .build();

        Page<VesselQueryResponse> page = vesselRepository.findAllBy(pageRequest, vesselQueryRequest);
        pageDto.setPage(pageDto.getPageNumber(), pageDto.getPageSize(), page.getTotalElements(), page.getTotalPages());

        return VesselResponse.VesselResult.builder()
                .content(page.getContent().stream()
                        .map(vesselResponseMapper::toVesselsDto)
                        .collect(Collectors.toList())
                )
                .page(pageDto)
                .search(searchDto)
                .build();
    }

    public VesselResponse.Vessel getVessel(final UUID vesselId) {
        return vesselResponseMapper.toVesselDto(this.getVesselByVesselId(vesselId));
    }

    @Transactional
    public void updateVessel(final UUID vesselId, final VesselRequest.UpdateVessel dto) {
        this.getVesselByVesselId(vesselId).update(
                dto.getVesselName(),
                dto.getVesselStatus(),
                dto.getTotalGuests(),
                dto.getCrew(),
                dto.getNumberCabins(),
                dto.getLength(),
                dto.getWidth(),
                dto.getHull(),
                dto.getCrusingSpeed(),
                dto.getEngine(),
                dto.getGenerator(),
                dto.getCompressor(),
                dto.getNitrox(),
                dto.getDinghy(),
                dto.getWaterMakers(),
                dto.getFreshWaterTank(),
                dto.getDieselTank(),
                dto.getRange()
        );
    }

    @Transactional
    public void updateVesselUsed(final UUID vesselId, final VesselRequest.changeVesselUsed dto) {
        this.getVesselByVesselId(vesselId).changeUsed(dto.getUsed());
    }

    public void deleteVessel(final UUID vesselId) {
        Vessel vessel = this.getVesselByVesselId(vesselId);
        if (!vessel.getVesselCabins().isEmpty()) {
            throw DiveTripError.VESSEL_CAN_NOT_DELETED.exception();
        }

        vesselRepository.delete(vessel);
    }

    public Vessel getVesselByVesselId(final UUID vesselId) {
        return vesselRepository.findById(vesselId)
                .orElseThrow(() ->  DiveTripError.VESSEL_NOT_FOUND.exception(vesselId.toString()));
    }

}
