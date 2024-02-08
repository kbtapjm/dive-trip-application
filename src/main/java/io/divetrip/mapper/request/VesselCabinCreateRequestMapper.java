package io.divetrip.mapper.request;

import io.divetrip.domain.entity.Vessel;
import io.divetrip.domain.entity.VesselCabin;
import io.divetrip.dto.request.VesselCabinRequest;
import io.divetrip.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VesselCabinCreateRequestMapper extends GenericMapper<VesselCabinRequest.CreateVesselCabin, VesselCabin> {

    @Mapping(source = "vessel", target = "vessel")
    VesselCabin toEntity(VesselCabinRequest.CreateVesselCabin createVesselCabin, Vessel vessel);
}
