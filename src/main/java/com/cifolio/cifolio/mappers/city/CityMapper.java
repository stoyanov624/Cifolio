package com.cifolio.cifolio.mappers.city;

import com.cifolio.cifolio.converters.city.CityDtoToEntityConverter;
import com.cifolio.cifolio.converters.city.CityEntityToDtoConverter;
import com.cifolio.cifolio.dtos.city.CityDto;
import com.cifolio.cifolio.models.city.City;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CityMapper {
    private final CityDtoToEntityConverter dtoToCityConverter;
    private final CityEntityToDtoConverter cityToDtoConverter;

    public City mapToEntity(CityDto cityDto) {
        return dtoToCityConverter.apply(cityDto);
    }

    public CityDto mapToDto(City city) {
        return cityToDtoConverter.apply(city);
    }

    public List<CityDto> mapToDtoList(Collection<City> entities) {
        return entities.stream().map(cityToDtoConverter).toList();
    }

    public List<City> toEntityList(Collection<CityDto> entities) {
        return entities.stream().map(dtoToCityConverter).toList();
    }
}
