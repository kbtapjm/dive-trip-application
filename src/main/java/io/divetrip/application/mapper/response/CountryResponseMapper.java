package io.divetrip.application.mapper.response;

import io.divetrip.application.dto.response.CountryResponse;
import io.divetrip.library.domain.entity.Country;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CountryResponseMapper {

    CountryResponse.Country toCountryDto(final Country country);

}
