package io.divetrip.exception.handler;

import io.divetrip.enumeration.ErrorCode;
import io.divetrip.exception.dto.ExceptionResponse;
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

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionResponse.of(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult()));
    }




}
