package io.divetrip.exception.handler;

import io.divetrip.enumeration.DiveTripError;
import io.divetrip.exception.dto.ExceptionResponse;
import io.divetrip.exception.error.DiveTripException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GolbalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.error("methodArgumentNotValidExceptionHandler: {}", e);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ExceptionResponse.of(DiveTripError.INVALID_INPUT_VALUE, e.getBindingResult()));
    }

    @ExceptionHandler(DiveTripException.class)
    public ResponseEntity<ExceptionResponse> diveTripExceptionHandler(DiveTripException e) {
        log.error("diveTripExceptionHandler: {}", e);

        return ResponseEntity
                .status(this.getHttpStatus(e.getDiveTripError().getStatus()))
                .body(ExceptionResponse.of(e.getDiveTripError()));
    }

    private HttpStatus getHttpStatus(int status) {
        HttpStatus httpStatus = null;

        if (HttpStatus.BAD_REQUEST.value() == status) {
            httpStatus =  HttpStatus.BAD_REQUEST;
        } else if (HttpStatus.NOT_FOUND.value() == status) {
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return httpStatus;
    }

}
