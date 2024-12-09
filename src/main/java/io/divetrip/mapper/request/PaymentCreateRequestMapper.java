package io.divetrip.mapper.request;

import io.divetrip.domain.entity.Payment;
import io.divetrip.domain.entity.TripReservation;
import io.divetrip.dto.request.PaymentRequest;
import io.divetrip.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentCreateRequestMapper extends GenericMapper<PaymentRequest.CreatePayment, Payment> {

    @Mapping(source = "tripReservation", target = "tripReservation")
    Payment toEntity(PaymentRequest.CreatePayment createPayment, TripReservation tripReservation, String paymentIp);
}
