package com.cifolio.cifolio.dtos.utillity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ViolationDto {
    private final String fieldName;
    private final String message;
}
