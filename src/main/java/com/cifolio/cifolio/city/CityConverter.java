package com.cifolio.cifolio.city;

import com.cifolio.cifolio.dtos.CityDto;
import com.cifolio.cifolio.model.City;

public final class CityConverter {
    private CityConverter() {}

    public static CityDto convertToCityDto(City city) {
        return new CityDto(city.getId(), city.getName(), city.getPhoto());
    }

    public static City convertToCityEntity(CityDto city) {
        return new City(city.getId(), city.getName(), city.getPhoto());
    }
}
