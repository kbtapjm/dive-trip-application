package io.divetrip.enumeration;

import io.divetrip.exception.error.DiveTripException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DiveTripError {
    // common
    INVALID_INPUT_VALUE("E001", "invalid.input.value", 400),
    INVALID_TYPE_VALUE("E002", "invalid.type.value", 400),
    RESOURCE_NOT_FOUND("E003", "resource.not.found", 404),
    METHOD_NOT_ALLOWED("E004", "method.not.allowed", 405),
    INTERNAL_SERVER_ERROR("E005", "internal.server.error", 500),

    // security
    UNAUTHORIZED("E011", "자격 증명에 실패하였습니다", 401),
    ACCESS_DENIED("E012", "알 수 없는 이유로 요청이 거절되었습니다", 403),
    UNKNOWN_TOKEN_VALUE("E013", "알 수 없는 토큰입니다", 401),
    INVALID_TOKEN_VALUE("E014", "잘못된 토큰입니다", 401),
    EXPIRED_TOKEN_VALUE("E015", "만료된 토큰입니다", 401),
    UNSUPPORTED_TOKEN_VALUE("E016", "지원하지 않는 토큰입니다", 401),

    // diver
    EMAIL_DUPLICATED("E101", "valid.email.already.exists", 400),
    DIVER_NOT_FOUND("E102", "valid.diver.not.found", 404),
    DIVER_PASSWORD_NOT_MATCH("E103", "valid.diver.password.notMatch", 400),
    DIVER_PASSWORD_CAN_NOT_SAME("E104", "valid.diver.password.notSame", 400),
    DIVER_ROLE_DUPLICATED("E105", "valid.diver-role.already.exists", 400),
    DIVER_ROLE_NOT_FOUND("E106", "valid.diver-role.not.found", 404),

    // vessel
    VESSEL_NOT_FOUND("E201", "valid.vessel.not.found", 404),
    VESSEL_NAME_DUPLICATED("E202", "valid.vessel-name.already.exists", 400),
    VESSEL_CAN_NOT_DELETED("E203", "valid.vessel.can.not.deleted", 400),
    VESSEL_CABIN_NOT_FOUND("E204", "valid.vessel-cabin.not.found", 404),
    VESSEL_CABIN_NAME_DUPLICATED("E205", "valid.vessel-cabin-name.already.exists", 400),

    // country
    COUNTRY_NOT_FOUND("E301", "valid.country.not.found", 404),

    // destination
    DESTINATION_NOT_FOUND("E401", "valid.destination.not.found", 404),

    // trip
    TRIP_NOT_FOUND("E501", "valid.trip.not.found", 404),
    TRIP_CAN_NOT_DELETED("E502", "valid.trip.can.not.deleted", 400),
    TRIP_LODGING_NOT_FOUND("E503", "valid.trip-lodging.not.found", 404),

    // trip reservation
    TRIP_RESERVATION_NOT_FOUND("E601", "valid.trip-reservation.not.found", 404),

    // role
    ROLE_CODE_DUPLICATED("E701", "valid.role-code.already.exists", 400),
    ROLE_NOT_FOUND("E702", "valid.role.not.found", 404);

    private final String code;
    private final String message;
    private final int status;

    /* exception method */
    public DiveTripException exception() {
        return new DiveTripException(this);
    }

    public DiveTripException exception(String... args) {
        return new DiveTripException(this, args);
    }

}
