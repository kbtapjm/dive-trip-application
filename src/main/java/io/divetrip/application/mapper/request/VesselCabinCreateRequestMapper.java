package io.divetrip.application.mapper.request;

import io.divetrip.domain.entity.Vessel;
import io.divetrip.domain.entity.VesselCabin;
import io.divetrip.application.dto.request.VesselCabinRequest;
import io.divetrip.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VesselCabinCreateRequestMapper extends GenericMapper<VesselCabinRequest.CreateVesselCabin, VesselCabin> {

    @Mapping(source = "createVesselCabin.cabinName", target = "cabinName")
    @Mapping(source = "createVesselCabin.description", target = "description")
    @Mapping(source = "createVesselCabin.size", target = "size")
    @Mapping(source = "createVesselCabin.maxOccupancy", target = "maxOccupancy")
    @Mapping(source = "createVesselCabin.bedding", target = "bedding")
    @Mapping(source = "createVesselCabin.ensuiteBathroom", target = "ensuiteBathroom")
    @Mapping(source = "createVesselCabin.aircon", target = "aircon")
    @Mapping(source = "createVesselCabin.used", target = "used")
    @Mapping(source = "vessel", target = "vessel")
    VesselCabin toEntity(VesselCabinRequest.CreateVesselCabin createVesselCabin, Vessel vessel);
}
