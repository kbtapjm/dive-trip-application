package io.divetrip.application.service;

import io.divetrip.application.dto.request.PaymentRequest;
import io.divetrip.application.enumeration.DiveTripError;
import io.divetrip.application.mapper.request.PaymentCreateRequestMapper;
import io.divetrip.application.util.IpUtils;
import io.divetrip.library.domain.entity.Payment;
import io.divetrip.library.domain.entity.TripReservation;
import io.divetrip.library.domain.entity.enumeration.PaymentStatus;
import io.divetrip.library.domain.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentCreateRequestMapper paymentCreateRequestMapper;

    public Payment createPayment(TripReservation tripReservation, PaymentRequest.CreatePayment dto) {
        /* Payment service integration */
        String paymentIp = IpUtils.getIpFromHeader();

        Payment payment = paymentCreateRequestMapper.toEntity(dto, PaymentStatus.COMPLETED, tripReservation, paymentIp);

        return paymentRepository.save(payment);
    }

    public List<Payment> getPayments(TripReservation tripReservation) {
        return paymentRepository.findByTripReservation(tripReservation);
    }

    public Payment getPayment(UUID paymentId) {
        return this.getPaymentByPaymentId(paymentId);
    }

    private Payment getPaymentByPaymentId(final UUID paymentId) {
        return paymentRepository.findById(paymentId)
                .orElseThrow(() ->  DiveTripError.TRIP_RESERVATION_PAYMENT_NOT_FOUND.exception(paymentId.toString()));
    }

}
