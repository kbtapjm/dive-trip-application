package io.divetrip.exception.dto;

import io.divetrip.enumeration.DiveTripError;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExceptionResponse {
    private String message;
    private String code;
    private int status;
    private List<FieldError> errors;

    public ExceptionResponse(final DiveTripError diveTripError) {
        this.message = diveTripError.getMessage();
        this.code = diveTripError.getCode();
        this.status = diveTripError.getStatus();
        this.errors = new ArrayList<>();
    }

    public ExceptionResponse(final DiveTripError diveTripError, final List<FieldError> errors) {
        this.message = diveTripError.getMessage();
        this.code = diveTripError.getCode();
        this.status = diveTripError.getStatus();
        this.errors = errors;
    }

    public static ExceptionResponse of(final DiveTripError diveTripError) {
        return new ExceptionResponse(diveTripError);
    }

    public static ExceptionResponse of(final DiveTripError errorCode, final BindingResult bindingResult) {
        return new ExceptionResponse(errorCode, FieldError.of(bindingResult));
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class FieldError {
        private String field;
        private String value;
        private String reason;

        public static List<FieldError> of(final String field, final String value, final String reason) {
            List<FieldError> fieldErrors = new ArrayList<>();
            fieldErrors.add(new FieldError(field, value, reason));

            return fieldErrors;
        }

        private static List<FieldError> of(final BindingResult bindingResult) {
            final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();

            return fieldErrors.stream()
                    .map(error -> new FieldError(
                            error.getField(),
                            !Objects.isNull(error.getRejectedValue()) ? error.getRejectedValue().toString(): StringUtils.EMPTY,
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }
    }
}
