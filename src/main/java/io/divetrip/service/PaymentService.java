package io.divetrip.service;

import io.divetrip.domain.entity.Payment;
import io.divetrip.domain.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public Payment createPayment(final Payment payment) {
        return paymentRepository.save(payment);
    }

}
