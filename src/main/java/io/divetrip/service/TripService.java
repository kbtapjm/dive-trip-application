package io.divetrip.service;

import io.divetrip.domain.entity.Destination;
import io.divetrip.domain.entity.Trip;
import io.divetrip.domain.entity.TripLodging;
import io.divetrip.domain.entity.TripSchedule;
import io.divetrip.domain.entity.Vessel;
import io.divetrip.domain.repository.TripRepository;
import io.divetrip.domain.repository.dto.request.TripQueryRequest;
import io.divetrip.domain.repository.dto.response.TripQueryResponse;
import io.divetrip.dto.PageDto;
import io.divetrip.dto.request.TripRequest;
import io.divetrip.dto.response.TripResponse;
import io.divetrip.enumeration.DiveTripError;
import io.divetrip.mapper.request.TripCreateRequestMapper;
import io.divetrip.mapper.request.TripLodgingRequestMapper;
import io.divetrip.mapper.request.TripScheduleRequestMapper;
import io.divetrip.mapper.request.TripStatusHistoryRequestMapper;
import io.divetrip.mapper.response.TripLodgingResponseMapper;
import io.divetrip.mapper.response.TripResponseMapper;
import io.divetrip.mapper.response.TripScheduleResponseMapper;
import io.divetrip.mapper.response.TripStatusHistoryResponseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TripService {
    private final TripRepository tripRepository;
    private final TripCreateRequestMapper tripCreateRequestMapper;
    private final TripScheduleRequestMapper tripScheduleRequestMapper;
    private final TripLodgingRequestMapper tripLodgingRequestMapper;
    private final TripStatusHistoryRequestMapper tripStatusHistoryRequestMapper;
    private final TripResponseMapper tripResponseMapper;
    private final TripScheduleResponseMapper tripScheduleResponseMapper;
    private final TripLodgingResponseMapper tripLodgingResponseMapper;
    private final TripStatusHistoryResponseMapper tripStatusHistoryResponseMapper;
    private final DestinationService destinationService;
    private final VesselService vesselService;

    @Transactional
    public String createTrip(final TripRequest.CreateTrip dto) {
        /* get destination */
        Destination destination = destinationService.getDestinationByDestinationId(dto.getDestinationId());

        /* get vessel */
        Vessel vessel = vesselService.getVesselByVesselId(dto.getVesselId());

        /* create trip */
        Trip trip = tripRepository.save(tripCreateRequestMapper.toEntity(dto, destination, vessel));

        /* create trip schedules */
        List<TripSchedule> tripSchedules = dto.getSchedules().stream()
                .map(m -> {
                    return tripScheduleRequestMapper.toEntity(m, trip);
                })
                .collect(Collectors.toList());
        trip.addAllSchedules(tripSchedules);

        /* create trip lodgings */
        List<TripLodging> tripLodgings = dto.getLodgings().stream()
                .map(m -> {
                    return tripLodgingRequestMapper.toEntity(m, trip);
                })
                .collect(Collectors.toList());
        trip.addAllLodgings(tripLodgings);

        /* create trip status history */
        trip.addAllStatusHistorys(List.of(tripStatusHistoryRequestMapper.toEntity(dto.getTripStatus(), StringUtils.EMPTY, trip)));

        return trip.getTripId().toString();
    }

    public TripResponse.TripResult getTrips(PageDto pageDto, TripRequest.SearchTrip searchDto) {
        PageRequest pageRequest = PageRequest.of(pageDto.getPageNumber(), pageDto.getPageSize(), searchDto.getPageSort());

        TripQueryRequest tripQueryRequest = TripQueryRequest.builder()
                .tripStatus(searchDto.getTripStatus())
                .area(searchDto.getArea())
                .build();

        Page<TripQueryResponse> page = tripRepository.findAllBy(pageRequest, tripQueryRequest);
        pageDto.setPage(pageDto.getPageNumber(), pageDto.getPageSize(), page.getTotalElements(), page.getTotalPages());

        return TripResponse.TripResult.builder()
                .content(page.getContent().stream()
                        .map(tripResponseMapper::toTripsDto)
                        .collect(Collectors.toList())
                )
                .page(pageDto)
                .search(searchDto)
                .build();
    }

    public TripResponse.Trip getTrip(final UUID tripId) {
        Trip trip = this.getTripByTripId(tripId);

        List<TripResponse.TripSchedule> schedules = trip.getSchedules().stream()
                .map(tripScheduleResponseMapper::toTripScheduleDto)
                .collect(Collectors.toList());

        List<TripResponse.TripLodging> lodgings = trip.getLodgings().stream()
                .map(tripLodgingResponseMapper::toTripLodgingDto)
                .collect(Collectors.toList());

        List<TripResponse.TripStatusHistory> statusHistorys = trip.getStatusHistorys().stream()
                .map(tripStatusHistoryResponseMapper::toStatusHistoryDto)
                .collect(Collectors.toList());

        return tripResponseMapper.toTripDto(trip, schedules, lodgings, statusHistorys);
    }

    private Trip getTripByTripId(final UUID tripId) {
        return tripRepository.findById(tripId)
                .orElseThrow(() ->  DiveTripError.TRIP_NOT_FOUND.exception(tripId.toString()));
    }

}
