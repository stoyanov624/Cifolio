package com.cifolio.cifolio.dto.guide;
import com.cifolio.cifolio.dto.city.CityDto;
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
