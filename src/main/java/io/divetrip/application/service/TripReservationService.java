package io.divetrip.application.service;

import io.divetrip.domain.entity.Diver;
import io.divetrip.domain.entity.Payment;
import io.divetrip.domain.entity.TripLodging;
import io.divetrip.domain.entity.TripReservation;
import io.divetrip.domain.entity.enumeration.PaymentStatus;
import io.divetrip.domain.entity.enumeration.ReservationStatus;
import io.divetrip.domain.repository.TripReservationRepository;
import io.divetrip.domain.repository.dto.request.TripReservationQueryRequest;
import io.divetrip.domain.repository.dto.response.TripReservationQueryResponse;
import io.divetrip.dto.PageDto;
import io.divetrip.application.dto.request.PaymentRequest;
import io.divetrip.application.dto.request.TripReservationRequest;
import io.divetrip.application.dto.response.PaymentResponse;
import io.divetrip.application.dto.response.TripReservationResponse;
import io.divetrip.application.dto.response.TripReservationStatusHistoryResponse;
import io.divetrip.application.enumeration.DiveTripError;
import io.divetrip.application.message.NotificationProducer;
import io.divetrip.application.mapper.request.TripReservationRequestMapper;
import io.divetrip.application.mapper.request.TripReservationStatusHistoryRequestMapper;
import io.divetrip.application.mapper.response.PaymentResponseMapper;
import io.divetrip.application.mapper.response.TripReservationResponseMapper;
import io.divetrip.application.mapper.response.TripReservationStatusHistoryResponseMapper;
import io.divetrip.message.model.Notification;
import io.divetrip.application.secuity.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TripReservationService {

    private final TripReservationRepository tripReservationRepository;

    private final TripReservationRequestMapper tripReservationRequestMapper;
    private final TripReservationResponseMapper tripReservationResponseMapper;
    private final TripReservationStatusHistoryRequestMapper tripReservationStatusHistoryRequestMapper;
    private final TripReservationStatusHistoryResponseMapper tripReservationStatusHistoryResponseMapper;
    private final PaymentResponseMapper paymentResponseMapper;

    private final DiverService diverService;
    private final TripLodgingService tripLodgingService;
    private final PaymentService paymentService;
    private final NotificationProducer notificationProducer;

    @Transactional
    public String createTripReservation(final TripReservationRequest.CreateTripReservation dto) {
        /* get trip lodging */
        TripLodging tripLodging = tripLodgingService.getTripLodgingById(dto.getTripLodgingId());

        /* get diver */
        Diver diver = diverService.getDiverByDiverId(dto.getDiverId());

        /* set trip reservation */
        if (tripReservationRepository.existsByDiverAndTripLodging(diver, tripLodging)) {
            throw DiveTripError.TRIP_RESERVATION_DUPLICATED.exception();
        }
        TripReservation tripReservation = tripReservationRepository.save(tripReservationRequestMapper.toEntity(dto, diver, tripLodging));

        /* add trip reservation status history */
        tripReservation.addStatusHistoryList(tripReservationStatusHistoryRequestMapper.toEntity(dto.getReservationStatus(), dto.getNote(), tripReservation));

        /* send trip reservation notification message */
        Notification notification = Notification.builder()
                .name("Trip Reservation Information")
                .message("Your travel reservation request has been completed")
                .createdBy(SecurityUtil.getUserId())
                .createdAt(LocalDateTime.now())
                .build();

        notificationProducer.sendWithCallback(notification);

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
            dto.getDepartureFlightNumbers(),
            dto.getDepartureFlightDate(),
            dto.getArrivalFlightNumbers(),
            dto.getArrivalFlightDate(),
            dto.getLastDiveDate(),
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

    @Transactional
    public void updateTripReservationStatus(final UUID tripReservationId, final TripReservationRequest.updateTripReservationStatus dto) {
        TripReservation tripReservation = this.getTripReservationById(tripReservationId);

        /* change trip reservation status */
        tripReservation.changeReservationStatus(dto.getReservationStatus());

        /* add trip reservation status history */
        tripReservation.addStatusHistoryList(
                tripReservationStatusHistoryRequestMapper.toEntity(dto.getReservationStatus(), dto.getNote(), tripReservation)
        );

        /* send trip reservation notification message */
        Notification notification = Notification.builder()
                .name("Trip Reservation Information")
                .message("Your travel reservation status has changed")
                .createdBy(SecurityUtil.getUserId())
                .createdAt(LocalDateTime.now())
                .build();

        notificationProducer.sendWithCallback(notification);
    }

    public List<TripReservationStatusHistoryResponse.TripReservationStatusHistorys> getTripReservationStatusHistoryList(final UUID tripReservationId) {
        TripReservation tripReservation = this.getTripReservationById(tripReservationId);

        return tripReservation.getStatusHistorys().stream()
                .map(tripReservationStatusHistoryResponseMapper::toListDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public String createTripReservationPayment(final UUID tripReservationId, final PaymentRequest.CreatePayment dto) {
        /* get trip reservation */
        TripReservation tripReservation = this.getTripReservationById(tripReservationId);
        if (tripReservation.getReservationStatus() != ReservationStatus.REQUESTED) {
            throw DiveTripError.TRIP_RESERVATION_PAYMENT_COULD_NOT_MADE.exception(tripReservationId.toString());
        }

        /* create trip reservation payment */
        Payment payment = paymentService.createPayment(tripReservation, dto);

        if (payment.getPaymentStatus() == PaymentStatus.COMPLETED) {
            /* trip reservation payment completed */
            tripReservation.tripReservationCompleted();

            /* add trip reservation status history */
            tripReservation.addStatusHistoryList(
                    tripReservationStatusHistoryRequestMapper.toEntity(ReservationStatus.COMPLETED, dto.getPaymentDetails(), tripReservation)
            );
        }

        return payment.getPaymentId().toString();
    }

    public List<PaymentResponse.Payments> getTripReservationPayments(final UUID tripReservationId) {
        TripReservation tripReservation = this.getTripReservationById(tripReservationId);

        return paymentService.getPayments(tripReservation).stream()
                .map(paymentResponseMapper::toPaymentsDto)
                .collect(Collectors.toList());
    }

    public PaymentResponse.Payment getTripReservationPayment(final UUID tripReservationId, final UUID paymentId) {
        this.getTripReservationById(tripReservationId);

        return paymentResponseMapper.toPaymentDto(paymentService.getPayment(paymentId));
    }

}
