package io.divetrip.application.mapper.response;

import io.divetrip.application.dto.response.VesselResponse;
import io.divetrip.library.domain.entity.Vessel;
import io.divetrip.library.domain.repository.dto.response.VesselQueryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VesselResponseMapper {

    VesselResponse.Vessel toVesselDto(final Vessel vessel);

    VesselResponse.Vessels toVesselsDto(final VesselQueryResponse vesselQueryResponse);

}
