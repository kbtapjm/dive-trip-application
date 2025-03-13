package io.divetrip.application.mapper.response;

import io.divetrip.application.dto.response.PaymentResponse;
import io.divetrip.library.domain.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentResponseMapper {

    PaymentResponse.Payments toPaymentsDto(Payment payment);

    PaymentResponse.Payment toPaymentDto(Payment payment);

}
