package io.divetrip.application.mapper.request;

import io.divetrip.application.dto.request.DestinationRequest;
import io.divetrip.application.mapper.GenericMapper;
import io.divetrip.library.domain.entity.Country;
import io.divetrip.library.domain.entity.Destination;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DestinationRequestMapper extends GenericMapper<DestinationRequest.CreateDestination, Destination> {

    @Mapping(source = "country", target = "country")
    Destination toEntity(DestinationRequest.CreateDestination createDestination, Country country);

}
