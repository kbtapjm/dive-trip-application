package io.divetrip.application.mapper.request;

import io.divetrip.application.dto.request.VesselRequest;
import io.divetrip.application.mapper.GenericMapper;
import io.divetrip.library.domain.entity.Vessel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VesselCreateRequestMapper extends GenericMapper<VesselRequest.CreateVessel, Vessel> {

}
