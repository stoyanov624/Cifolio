package com.cifolio.cifolio.city;

import com.cifolio.cifolio.dtos.CityDto;
import com.cifolio.cifolio.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.stream.Collectors;

@Service
public class CityService {
    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public Page<CityDto> getCitiesPage(String name, Pageable pagingData) {
        Page<City> cities = StringUtils.hasText(name) ? cityRepository.findAllOnPageByNameIsContaining(name, pagingData) : cityRepository.findAll(pagingData);
        return new PageImpl(
                cities.getContent()
                        .stream()
                        .map(CityConverter::convertToCityDto)
                        .collect(Collectors.toList()),
                pagingData, cities.getTotalElements());
    }

    public void updateCity(CityDto city) {
        cityRepository.findById(city.getId())
            .map(existingCity -> CityConverter.convertToCityEntity(city))
            .map(cityRepository::save)
            .orElseThrow(() -> new IllegalArgumentException("Unable to update! City with ID: " + city.getId() + " not found!"));
    }
}
