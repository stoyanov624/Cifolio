package com.cifolio.cifolio.dto.guide;

import com.cifolio.cifolio.dto.city.CityDto;
import lombok.Data;

import java.util.Set;

@Data
public class TravelGuideDto {
    private String name;
    private Set<CityDto> cities;
}
