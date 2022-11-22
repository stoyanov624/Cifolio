package com.cifolio.cifolio.dto;

import com.cifolio.cifolio.model.city.TravelGuide;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@Setter
@Getter
public class CityDto {
    private Long id;
    private String name;
    private String photo;
}
