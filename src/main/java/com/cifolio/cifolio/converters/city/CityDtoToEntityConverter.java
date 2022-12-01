package com.cifolio.cifolio.converters.city;

import com.cifolio.cifolio.dtos.city.CityDto;
import com.cifolio.cifolio.models.city.City;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.function.Function;

@Component
public class CityDtoToEntityConverter implements Function<CityDto, City> {
    @Override
    public City apply(CityDto cityDto) {
        return new City(cityDto.getId(), cityDto.getName(), cityDto.getPhoto(), new HashSet<>());
    }
}
