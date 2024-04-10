package io.divetrip.exception.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExceptionResponse {
    private String message;
    private String code;
    private int status;
    private List<FieldError> errors;

    @Builder
    public ExceptionResponse(String message, String code, int status, List<FieldError> errors) {
        this.message = message;
        this.code = code;
        this.status = status;
        this.errors = errors;
    }

    public static ExceptionResponse of(final String code, String message, int status) {
        return ExceptionResponse.builder()
                .code(code)
                .message(message)
                .status(status)
                .build();
    }

    public static ExceptionResponse of(final String code, String message, int status, List<FieldError> errors) {
        return ExceptionResponse.builder()
                .code(code)
                .message(message)
                .status(status)
                .errors(errors)
                .build();
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class FieldError {
        private String field;
        private String value;
        private String reason;

        public static List<FieldError> of(final BindingResult bindingResult) {
            final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();

            return fieldErrors.stream()
                    .map(error -> {
                        return FieldError.builder()
                                .field(error.getField())
                                .value(!Objects.isNull(error.getRejectedValue()) ? error.getRejectedValue().toString(): StringUtils.EMPTY)
                                .reason(error.getDefaultMessage())
                                .build();
                    })
                    .collect(Collectors.toList());
        }
    }
}
