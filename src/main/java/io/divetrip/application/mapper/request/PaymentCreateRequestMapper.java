package io.divetrip.application.mapper.request;


import io.divetrip.application.dto.request.PaymentRequest;
import io.divetrip.library.domain.entity.Payment;
import io.divetrip.library.domain.entity.TripReservation;
import io.divetrip.library.domain.entity.enumeration.PaymentStatus;
import io.divetrip.library.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentCreateRequestMapper extends GenericMapper<PaymentRequest.CreatePayment, Payment> {

    @Mapping(source = "tripReservation", target = "tripReservation")
    @Mapping(source = "paymentStatus", target = "paymentStatus")
    Payment toEntity(PaymentRequest.CreatePayment createPayment, PaymentStatus paymentStatus, TripReservation tripReservation, String paymentIp);
}
