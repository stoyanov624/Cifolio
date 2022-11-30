package com.cifolio.cifolio.exception_handling;

import com.cifolio.cifolio.exception_handling.exceptions.CityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<Object> handleCityNotFound(CityNotFoundException exception) {
        return new ResponseEntity<>(new ApiError(
                exception.getMessage(),
                HttpStatus.NOT_FOUND,
                LocalDateTime.now()), HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleNullPointerException(NullPointerException exception) {
        ApiError error = new ApiError(exception.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}