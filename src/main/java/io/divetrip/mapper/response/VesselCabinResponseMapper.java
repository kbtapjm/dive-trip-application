package io.divetrip.mapper.response;

import io.divetrip.domain.entity.VesselCabin;
import io.divetrip.dto.response.VesselCabinResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VesselCabinResponseMapper {

    VesselCabinResponse.VesselCabins toVesselCabins(final VesselCabin vesselCabin);

    VesselCabinResponse.VesselCabin toVesselCabin(final VesselCabin vesselCabin);

}
