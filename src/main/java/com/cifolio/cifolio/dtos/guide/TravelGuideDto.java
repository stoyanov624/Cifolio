package com.cifolio.cifolio.dtos.guide;
import com.cifolio.cifolio.dtos.city.CityDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TravelGuideDto {
    private Long id;
    private String name;
    private List<CityDto> cities;
}
