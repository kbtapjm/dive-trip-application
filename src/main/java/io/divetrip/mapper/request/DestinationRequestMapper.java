package io.divetrip.mapper.request;

import io.divetrip.domain.entity.Country;
import io.divetrip.domain.entity.Destination;
import io.divetrip.dto.request.DestinationRequest;
import io.divetrip.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DestinationRequestMapper extends GenericMapper<DestinationRequest.CreateDestination, Destination> {

    @Mapping(source = "country", target = "country")
    Destination toEntity(DestinationRequest.CreateDestination createDestination, Country country);

}
