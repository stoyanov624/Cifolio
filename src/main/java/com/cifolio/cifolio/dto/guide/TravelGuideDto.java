package com.cifolio.cifolio.dto.guide;

import java.util.List;
import com.cifolio.cifolio.dto.city.CityDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TravelGuideDto {
    private String name;
    private List<CityDto> cities;
}
