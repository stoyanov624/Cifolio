package com.cifolio.cifolio.service.city;

import com.cifolio.cifolio.models.city.City;
import com.cifolio.cifolio.repositories.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityNotFoundException;
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
            .orElseThrow(() -> new EntityNotFoundException("Unable to update! City with ID: " + city.getId() + " not found!"));

        cityRepository.save(city);
    }

    public Collection<City> getCities() {
        return cityRepository.findAll();
    }
}
