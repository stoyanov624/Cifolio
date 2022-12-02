package com.cifolio.cifolio.dtos.city;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CityDto {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String photo;
}
