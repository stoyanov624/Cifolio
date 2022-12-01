package com.cifolio.cifolio.exception_handling;

import com.cifolio.cifolio.dtos.utillity.ErrorDto;
import com.cifolio.cifolio.dtos.utillity.ViolationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
@Slf4j
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleCityNotFound(EntityNotFoundException exception) {
        log.error("EntityNotFoundException : ", exception);
        return buildCustomResponseEntityOnError(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleNullPointerException(NullPointerException exception) {
        log.error("NullPointerException : ", exception);
        return buildCustomResponseEntityOnError(exception, BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception) {
        List<ViolationDto> error = new ArrayList<>();
        for (ConstraintViolation violation : exception.getConstraintViolations()) {
            error.add(new ViolationDto(violation.getPropertyPath().toString(), violation.getMessage()));
        }
        return new ResponseEntity<>(error, BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ViolationDto> error = new ArrayList<>();
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            error.add(new ViolationDto(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return new ResponseEntity<>(error, BAD_REQUEST);
    }

    private ResponseEntity<Object> buildCustomResponseEntityOnError(Exception exception, HttpStatus status) {
        ErrorDto error = new ErrorDto(exception.getMessage(), status, LocalDateTime.now());
        return new ResponseEntity<>(error, status);
    }
}
