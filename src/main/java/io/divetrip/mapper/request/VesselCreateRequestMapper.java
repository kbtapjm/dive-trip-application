package io.divetrip.mapper.request;

import io.divetrip.domain.entity.Vessel;
import io.divetrip.dto.request.VesselRequest;
import io.divetrip.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VesselCreateRequestMapper extends GenericMapper<VesselRequest.CreateVessel, Vessel> {

}
