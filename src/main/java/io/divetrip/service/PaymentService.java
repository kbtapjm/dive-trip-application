package io.divetrip.service;

import io.divetrip.domain.repository.PaymentRepository;
import io.divetrip.mapper.request.PaymentCreateRequestMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentCreateRequestMapper paymentCreateRequestMapper;

}
