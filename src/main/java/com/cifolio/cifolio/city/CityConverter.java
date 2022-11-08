package com.cifolio.cifolio.city;

import com.cifolio.cifolio.dtos.CityDto;
import com.cifolio.cifolio.model.City;
import org.springframework.stereotype.Component;

@Component
public class CityConverter {
    public CityDto convertToCityDto(City city) {
        return new CityDto(city.getId(), city.getName(), city.getPhoto());
    }

    public City convertToCityEntity(CityDto city) {
        return new City(city.getId(), city.getName(), city.getPhoto());
    }
}
