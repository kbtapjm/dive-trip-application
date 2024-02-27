package io.divetrip.service;

import io.divetrip.domain.entity.Country;
import io.divetrip.domain.entity.Destination;
import io.divetrip.domain.repository.DestinationRepository;
import io.divetrip.domain.repository.dto.request.DestinationQueryRequest;
import io.divetrip.domain.repository.dto.response.DestinationQueryResponse;
import io.divetrip.dto.request.DestinationRequest;
import io.divetrip.dto.response.DestinationResponse;
import io.divetrip.enumeration.DiveTripError;
import io.divetrip.mapper.request.DestinationRequestMapper;
import io.divetrip.mapper.response.DestinationResponseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DestinationService {

    private final DestinationRepository destinationRepository;
    private final DestinationRequestMapper destinationRequestMapper;
    private final DestinationResponseMapper destinationResponseMapper;
    private final CountryService countryService;

    @Transactional
    public String createDestination(final DestinationRequest.CreateDestination dto) {
        Country country = countryService.getCountry(dto.getCountryId());

        Destination destination = destinationRepository.save(destinationRequestMapper.toEntity(dto, country));

        return destination.getDestinationId().toString();
    }

    public List<DestinationResponse.Destinations> getDestinations(final DestinationRequest.SearchDestination dto) {
        DestinationQueryRequest queryRequest = DestinationQueryRequest.builder()
                .continent(dto.getContinent())
                .area(dto.getArea())
                .countryId(dto.getCountryId())
                .build();

        List<DestinationQueryResponse> queryResponse = destinationRepository.findAllBy(queryRequest);

        return queryResponse.stream()
                .map(destinationResponseMapper::toDestinationsDto)
                .collect(Collectors.toList());
    }

    public DestinationResponse.Destination getDestinationById(final UUID destinationId) {
        return destinationResponseMapper.toDestinationDto(this.getDestinationByDestinationId(destinationId));
    }

    @Transactional
    public void updateDestination(final UUID destinationId, final DestinationRequest.UpdateDestination dto) {
        this.getDestinationByDestinationId(destinationId).update(
                dto.getContinent(),
                dto.getArea(),
                dto.getDescription(),
                countryService.getCountry(dto.getCountryId())
        );
    }

    public void deleteDestination(final UUID destinationId) {
        destinationRepository.delete(this.getDestinationByDestinationId(destinationId));
    }

    private Destination getDestinationByDestinationId(final UUID destinationId) {
        return destinationRepository.findById(destinationId)
                .orElseThrow(() ->  DiveTripError.DESTINATION_NOT_FOUND.exception(destinationId.toString()));
    }

}
