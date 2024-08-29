package io.divetrip.exception.handler;

import io.divetrip.enumeration.DiveTripError;
import io.divetrip.exception.dto.ExceptionResponse;
import io.divetrip.exception.error.DiveTripException;
import io.divetrip.util.MessageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class GolbalExceptionHandler {

    private final MessageUtils messageUtils;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.error("methodArgumentNotValidExceptionHandler: {}", e);

        DiveTripError error = DiveTripError.INVALID_INPUT_VALUE;

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ExceptionResponse.of(error.getCode(), messageUtils.getMessage(error.getMessage()), error.getStatus(), ExceptionResponse.FieldError.of(e.getBindingResult())));
    }

    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseEntity<ExceptionResponse> authenticationExceptionHandler(AuthenticationException e) {
        log.error("authenticationExceptionHandler: {}", e);

        DiveTripError error = DiveTripError.UNAUTHORIZED;

        return ResponseEntity
                .status(this.getHttpStatus(error.getStatus()))
                .body(ExceptionResponse.of(error.getCode(), e.getMessage(), error.getStatus()));
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<ExceptionResponse> accessDeniedExceptionHandler(AccessDeniedException e) {
        log.error("accessDeniedExceptionHandler: {}", e);

        DiveTripError error = DiveTripError.ACCESS_DENIED;

        return ResponseEntity
                .status(this.getHttpStatus(error.getStatus()))
                .body(ExceptionResponse.of(error.getCode(), e.getMessage(), error.getStatus()));
    }

    @ExceptionHandler(DiveTripException.class)
    public ResponseEntity<ExceptionResponse> diveTripExceptionHandler(DiveTripException e) {
        log.error("diveTripExceptionHandler: {}", e);

        DiveTripError error = e.getDiveTripError();

        return ResponseEntity
                .status(this.getHttpStatus(error.getStatus()))
                .body(ExceptionResponse.of(error.getCode(), messageUtils.getMessage(error.getMessage(), e.getArgs()), error.getStatus()));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ExceptionResponse> exceptionHandler(Exception e) {
        log.error("exceptionHandler: {}", e);

        DiveTripError error = DiveTripError.INTERNAL_SERVER_ERROR;

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ExceptionResponse.of(error.getCode(), e.getMessage(), error.getStatus()));
    }

    private HttpStatus getHttpStatus(int status) {
        return switch (status) {
            case 400 -> HttpStatus.BAD_REQUEST;
            case 401 -> HttpStatus.UNAUTHORIZED;
            case 403 -> HttpStatus.FORBIDDEN;
            case 404 -> HttpStatus.NOT_FOUND;
            case 405 -> HttpStatus.METHOD_NOT_ALLOWED;
            case 406 -> HttpStatus.NOT_ACCEPTABLE;
            case 500 -> HttpStatus.INTERNAL_SERVER_ERROR;
            default -> null;
        };
    }

}
