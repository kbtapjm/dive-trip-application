package io.divetrip.application.mapper.response;

import io.divetrip.application.dto.response.VesselCabinResponse;
import io.divetrip.library.domain.entity.VesselCabin;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VesselCabinResponseMapper {

    VesselCabinResponse.VesselCabins toVesselCabins(final VesselCabin vesselCabin);

    VesselCabinResponse.VesselCabin toVesselCabin(final VesselCabin vesselCabin);

}
