package com.cifolio.cifolio.service.city;

import com.cifolio.cifolio.model.city.City;
import com.cifolio.cifolio.repository.CityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import lombok.AllArgsConstructor;

import java.util.Collection;

@Service
@AllArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

    public Page<City> getCitiesPage(String name, Pageable pagingData) {
        return StringUtils.hasText(name) ? cityRepository.findAllOnPageByNameIsContaining(name, pagingData) : cityRepository.findAll(pagingData);
    }

    public void updateCity(City city) {
        cityRepository.findById(city.getId())
            .orElseThrow(() -> new IllegalArgumentException("Unable to update! City with ID: " + city.getId() + " not found!"));

        cityRepository.save(city);
    }

    public Collection<City> getCities() {
        return cityRepository.findAll();
    }
}
