package com.cifolio.cifolio.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CityService {
    private final CityRepository cityRepository;
    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }
    public Page<City> getCitiesPage(String name, Pageable pagingData) {
        return cityRepository.findAllOnPageByNameIsContaining(name, pagingData);
    }
    public Page<City> getCitiesPage(Pageable pagingData) {
        return cityRepository.findAll(pagingData);
    }
    public void updateCity(City city) {
        Optional<City> existingCity = cityRepository.findById((city.getId()));

        if(existingCity.isEmpty()) {
            throw new IllegalArgumentException("Unable to update! City with ID: " + city.getId() + " not found!");
        }

        existingCity.get().setName(city.getName());
        existingCity.get().setPhoto(city.getPhoto());
        cityRepository.save(existingCity.get());
    }
}
