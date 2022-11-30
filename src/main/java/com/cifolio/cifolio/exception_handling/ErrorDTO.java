package com.cifolio.cifolio.exception_handling;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ErrorDTO {
    private String message;
    private HttpStatus status;
    private LocalDateTime timestamp;
}
