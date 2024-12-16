package io.divetrip.service;

import io.divetrip.domain.entity.Payment;
import io.divetrip.domain.entity.TripReservation;
import io.divetrip.domain.entity.enumeration.PaymentStatus;
import io.divetrip.domain.repository.PaymentRepository;
import io.divetrip.dto.request.PaymentRequest;
import io.divetrip.enumeration.DiveTripError;
import io.divetrip.mapper.request.PaymentCreateRequestMapper;
import io.divetrip.util.IpUtils;
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
