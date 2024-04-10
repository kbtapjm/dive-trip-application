package io.divetrip.service;

import io.divetrip.domain.entity.Country;
import io.divetrip.domain.repository.CountryRepository;
import io.divetrip.dto.response.CountryResponse;
import io.divetrip.enumeration.DiveTripError;
import io.divetrip.mapper.response.CountryResponseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;
    private final CountryResponseMapper countryResponseMapper;

    public List<CountryResponse.Country> getCountryAll() {
        return countryRepository.findAll().stream()
                .map(countryResponseMapper::toCountryDto)
                .collect(Collectors.toList());
    }

    public Country getCountry(final Long countryId) {
        return countryRepository.findById(countryId)
                .orElseThrow(() ->  DiveTripError.COUNTRY_NOT_FOUND.exception(String.valueOf(countryId)));
    }
}
