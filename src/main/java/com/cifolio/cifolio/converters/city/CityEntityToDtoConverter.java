package com.cifolio.cifolio.converters.city;

import com.cifolio.cifolio.dto.city.CityDto;
import com.cifolio.cifolio.model.city.City;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CityEntityToDtoConverter implements Function<City, CityDto> {
    @Override
    public CityDto apply(City city) {
        return convertToCityEntity(city);
    }

    private CityDto convertToCityEntity(City city) {
        return new CityDto(city.getId(), city.getName(), city.getPhoto());
    }
}
