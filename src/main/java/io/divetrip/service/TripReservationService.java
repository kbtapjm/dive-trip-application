package io.divetrip.service;

import io.divetrip.domain.entity.Diver;
import io.divetrip.domain.entity.TripLodging;
import io.divetrip.domain.entity.TripReservation;
import io.divetrip.domain.repository.TripReservationRepository;
import io.divetrip.domain.repository.dto.request.TripReservationQueryRequest;
import io.divetrip.domain.repository.dto.response.TripReservationQueryResponse;
import io.divetrip.dto.PageDto;
import io.divetrip.dto.request.TripReservationRequest;
import io.divetrip.dto.response.TripReservationResponse;
import io.divetrip.enumeration.DiveTripError;
import io.divetrip.mapper.request.TripReservationRequestMapper;
import io.divetrip.mapper.request.TripReservationStatusHistoryRequestMapper;
import io.divetrip.mapper.response.TripReservationResponseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TripReservationService {

    private final TripReservationRepository tripReservationRepository;
    private final TripReservationRequestMapper tripReservationRequestMapper;
    private final TripReservationStatusHistoryRequestMapper tripReservationStatusHistoryRequestMapper;
    private final TripReservationResponseMapper tripReservationResponseMapper;
    private final DiverService diverService;
    private final TripLodgingService tripLodgingService;

    @Transactional
    public String createTripReservation(final TripReservationRequest.CreateTripReservation dto) {
        /* get trip lodging */
        TripLodging tripLodging = tripLodgingService.getTripLodgingById(dto.getTripLodgingId());

        /* get diver */
        Diver diver = diverService.getDiverByDiverId(dto.getDiverId());

        /* set trip reservation */
        TripReservation tripReservation = tripReservationRepository.save(tripReservationRequestMapper.toEntity(dto, diver, tripLodging));

        /* set trip reservation status history */
        tripReservation.addStatusHistorys(tripReservationStatusHistoryRequestMapper.toEntity(dto.getReservationStatus(), StringUtils.EMPTY, tripReservation));

        return tripReservation.getTripReservationId().toString();
    }

    public TripReservationResponse.TripReservationResult getTripReservations(PageDto pageDto, TripReservationRequest.SearchTripReservation searchDto) {
        PageRequest pageRequest = PageRequest.of(pageDto.getPageNumber(), pageDto.getPageSize(), searchDto.getPageSort());

        TripReservationQueryRequest tripReservationQueryRequest = TripReservationQueryRequest.builder()
            .reservationStatus(searchDto.getReservationStatus())
            .build();

        Page<TripReservationQueryResponse> page = tripReservationRepository.findAllBy(pageRequest, tripReservationQueryRequest);
        pageDto.setPage(page.getTotalElements(), page.getTotalPages());

        return TripReservationResponse.TripReservationResult.builder()
            .content(page.getContent().stream().map(tripReservationResponseMapper::toTripReservationsDto).collect(Collectors.toList()))
            .page(pageDto)
            .search(searchDto)
            .build();
    }

    public TripReservationResponse.TripReservation getTripReservation(final UUID tripReservationId) {
        TripReservation tripReservation = this.getTripReservationById(tripReservationId);

        return tripReservationResponseMapper.toTripReservationDto(tripReservation);
    }

    @Transactional
    public void updateTripReservation(final UUID tripReservationId, final TripReservationRequest.updateTripReservation dto) {
        TripReservation tripReservation = this.getTripReservationById(tripReservationId);

        tripReservation.update(
            dto.getReservationStatus(),
            dto.getPaid(),
            dto.getDepartureFlightNumbers(),
            dto.getDepartureFlightDate(),
            dto.getArrivalFlightNumbers(),
            dto.getArrivalFlightDate(),
            dto.getLastDiveDate(),
            dto.getAgreeTerms(),
            dto.getNote()
        );
    }

    public void deleteTripReservation(final UUID tripReservationId) {
        TripReservation tripReservation = this.getTripReservationById(tripReservationId);

        tripReservationRepository.delete(tripReservation);
    }

    private TripReservation getTripReservationById(final UUID tripReservationId) {
        return tripReservationRepository.findById(tripReservationId)
            .orElseThrow(() ->  DiveTripError.TRIP_RESERVATION_NOT_FOUND.exception(tripReservationId.toString()));
    }

}
