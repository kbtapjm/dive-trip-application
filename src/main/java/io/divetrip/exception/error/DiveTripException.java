package io.divetrip.exception.error;

import io.divetrip.enumeration.DiveTripError;
import lombok.Getter;

import java.io.Serial;

@Getter
public class DiveTripException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -5383993184969420664L;

    private final DiveTripError diveTripError;
    private final String[] args;

    public DiveTripException(DiveTripError diveTripError) {
        super(diveTripError.getMessage());
        this.diveTripError = diveTripError;
        args = new String[0];
    }

    public DiveTripException(DiveTripError diveTripError, String... args) {
        super(diveTripError.getMessage());
        this.diveTripError = diveTripError;
        this.args = args;
    }

}
