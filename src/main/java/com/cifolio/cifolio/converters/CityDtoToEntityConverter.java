package com.cifolio.cifolio.converters;

import com.cifolio.cifolio.dtos.CityDto;
import com.cifolio.cifolio.model.City;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public final class CityDtoToEntityConverter implements Function<CityDto, City> {
    @Override
    public City apply(CityDto cityDto) {
        return convertToCityEntity(cityDto);
    }

    private City convertToCityEntity(CityDto city) {
        return new City(city.getId(), city.getName(), city.getPhoto());
    }
}
