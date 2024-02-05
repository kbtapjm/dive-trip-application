package io.divetrip.mapper;

import io.divetrip.domain.entity.Vessel;
import io.divetrip.domain.repository.dto.response.VesselQueryResponse;
import io.divetrip.dto.response.VesselResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VesselResponseMapper {

    VesselResponse.Vessel toVesselDto(final Vessel vessel);

    VesselResponse.Vessels toVesselsDto(final VesselQueryResponse vesselQueryResponse);

}
