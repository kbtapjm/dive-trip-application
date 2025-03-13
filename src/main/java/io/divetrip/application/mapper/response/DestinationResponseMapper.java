package io.divetrip.application.mapper.response;

import io.divetrip.application.dto.response.DestinationResponse;
import io.divetrip.library.domain.entity.Destination;
import io.divetrip.library.domain.repository.dto.response.DestinationQueryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DestinationResponseMapper {

    @Mapping(source = "destination.country.countryId", target = "countryId")
    @Mapping(source = "destination.country.countryName", target = "countryName")
    DestinationResponse.Destination toDestinationDto(final Destination destination);

    DestinationResponse.Destinations toDestinationsDto(final DestinationQueryResponse destinationQueryResponse);

}
