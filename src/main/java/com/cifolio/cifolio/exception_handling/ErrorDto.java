package com.cifolio.cifolio.exception_handling;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ErrorDto {
    private final String message;
    private final HttpStatus status;
    private final LocalDateTime timestamp;
}
