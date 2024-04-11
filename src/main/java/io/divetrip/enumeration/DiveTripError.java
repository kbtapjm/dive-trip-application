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

    // diver
    EMAIL_DUPLICATED("E101", "valid.email.already.exists", 400),
    DIVER_NOT_FOUND("E102", "valid.diver.not.found", 404),
    DIVER_PASSWORD_NOT_MATCH("E103", "valid.diver.password.notMatch", 400),
    DIVER_PASSWORD_CAN_NOT_SAME("E104", "valid.diver.password.notSame", 400),

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
    TRIP_CAN_NOT_DELETED("E502", "valid.trip.can.not.deleted", 400)
    ;

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
