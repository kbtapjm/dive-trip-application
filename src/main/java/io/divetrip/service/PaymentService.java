package io.divetrip.service;

import io.divetrip.domain.entity.Payment;
import io.divetrip.domain.entity.TripReservation;
import io.divetrip.domain.repository.PaymentRepository;
import io.divetrip.enumeration.DiveTripError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public Payment createPayment(final Payment payment) {
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
