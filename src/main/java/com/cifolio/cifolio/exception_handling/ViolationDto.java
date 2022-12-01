package com.cifolio.cifolio.exception_handling;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ViolationDto {
    private final String fieldName;
    private final String message;
}
