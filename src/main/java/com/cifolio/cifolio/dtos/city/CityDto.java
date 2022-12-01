package com.cifolio.cifolio.dtos.city;

import lombok.*;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public class CityDto {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String photo;
}
