package io.divetrip.mapper.response;

import io.divetrip.domain.entity.Country;
import io.divetrip.dto.response.CountryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CountryResponseMapper {

    CountryResponse.Country toCountryDto(final Country country);

}
