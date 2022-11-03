package com.cifolio.cifolio.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/cities")
public class CityController {
    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public Page<City> getCitiesPage(
            @RequestParam(required = false) String cityName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size) {

        Pageable pagingData = PageRequest.of(page, size);
        if (cityName == null || cityName.isEmpty() || cityName.trim().isEmpty()) {
            return cityService.getCitiesPage(pagingData);
        }

        return cityService.getCitiesPage(cityName, pagingData);
    }
}
