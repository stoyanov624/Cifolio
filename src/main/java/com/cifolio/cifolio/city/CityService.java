package com.cifolio.cifolio.city;

import com.cifolio.cifolio.dtos.CityDto;
import com.cifolio.cifolio.model.City;
import liquibase.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityService {
    private final CityRepository cityRepository;
    private final CityConverter cityConverter;

    @Autowired
    public CityService(CityRepository cityRepository, CityConverter cityConverter) {
        this.cityRepository = cityRepository;
        this.cityConverter = cityConverter;
    }

    public Page<CityDto> getCitiesPage(String name, Pageable pagingData) {
        Page<City> cities = StringUtils.hasText(name) ? cityRepository.findAllOnPageByNameIsContaining(name, pagingData) : cityRepository.findAll(pagingData);
        return new PageImpl(
                cities.getContent()
                        .stream()
                        .map(cityConverter::convertToCityDto)
                        .collect(Collectors.toList()),
                pagingData, cities.getTotalElements());
    }

    public void updateCity(CityDto city) {
        cityRepository.findById(city.getId())
            .map(existingCity -> cityConverter.convertToCityEntity(city))
            .map(cityRepository::save)
            .orElseThrow(() -> new IllegalArgumentException("Unable to update! City with ID: " + city.getId() + " not found!"));
    }
}
