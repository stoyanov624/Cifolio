package com.cifolio.cifolio.converters.city;

import com.cifolio.cifolio.dto.city.CityDto;
import com.cifolio.cifolio.model.city.City;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.function.Function;

@Component
public class CityDtoToEntityConverter implements Function<CityDto, City> {
    @Override
    public City apply(CityDto cityDto) {
        return convertToEntity(cityDto);
    }

    private City convertToEntity(CityDto city) {
        return new City(city.getId(), city.getName(), city.getPhoto(), new HashSet<>());
    }
}
