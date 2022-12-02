package com.cifolio.cifolio.converters.city;

import com.cifolio.cifolio.dtos.city.CityDto;
import com.cifolio.cifolio.models.city.City;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CityEntityToDtoConverter implements Function<City, CityDto> {
    @Override
    public CityDto apply(City city) {
        return CityDto
            .builder()
            .id(city.getId())
            .name(city.getName())
            .photo(city.getPhoto())
            .build();
    }
}
