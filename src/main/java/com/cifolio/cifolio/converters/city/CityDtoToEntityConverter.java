package com.cifolio.cifolio.converters.city;

import com.cifolio.cifolio.dtos.city.CityDto;
import com.cifolio.cifolio.models.city.City;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CityDtoToEntityConverter implements Function<CityDto, City> {
    @Override
    public City apply(CityDto cityDto) {
        return City.builder()
            .id(cityDto.getId())
            .name(cityDto.getName())
            .photo(cityDto.getPhoto())
            .build();
    }
}
