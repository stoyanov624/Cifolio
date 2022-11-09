package com.cifolio.cifolio.city;

import com.cifolio.cifolio.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
public class CityService {
    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public Page<City> getCitiesPage(String name, Pageable pagingData) {
        return StringUtils.hasText(name) ? cityRepository.findAllOnPageByNameIsContaining(name, pagingData) : cityRepository.findAll(pagingData);
    }

    public void updateCity(City city) {
        cityRepository.findById(city.getId())
            .map(cityRepository::save)
            .orElseThrow(() -> new IllegalArgumentException("Unable to update! City with ID: " + city.getId() + " not found!"));
    }
}
