package com.cifolio.cifolio.mappers.city;

import com.cifolio.cifolio.converters.city.CityDtoToEntityConverter;
import com.cifolio.cifolio.converters.city.CityEntityToDtoConverter;
import com.cifolio.cifolio.dtos.city.CityDto;
import com.cifolio.cifolio.models.city.City;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CityMapper {
    private final CityDtoToEntityConverter dtoToCityConverter;
    private final CityEntityToDtoConverter cityToDtoConverter;

    public City mapCityDtoToEntity(CityDto cityDto) {
        return dtoToCityConverter.apply(cityDto);
    }

    public List<CityDto> mapCityEntitiesToDtos(Collection<City> entities) {
        return entities
                .stream()
                .map(cityToDtoConverter)
                .collect(Collectors.toList());
    }

    public List<City> mapCityDtosToEntities(Collection<CityDto> entities) {
        return entities
                .stream()
                .map(dtoToCityConverter)
                .collect(Collectors.toList());
    }
}
