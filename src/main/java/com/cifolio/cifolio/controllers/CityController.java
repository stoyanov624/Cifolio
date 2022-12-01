package com.cifolio.cifolio.controllers;

import com.cifolio.cifolio.mappers.city.CityMapper;
import com.cifolio.cifolio.service.city.CityService;
import com.cifolio.cifolio.dtos.city.CityDto;
import com.cifolio.cifolio.models.city.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.cifolio.cifolio.constants.CityConstants.*;
import static com.cifolio.cifolio.constants.UserConstants.ADMIN_ROLE;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;
    private final CityMapper cityMapper;

    @GetMapping("/cities-page")
    public ResponseEntity<Page<CityDto>> getCitiesPage(
            @RequestParam(required = false) String cityName,
            @PageableDefault(size = DEFAULT_PAGE_SIZE) Pageable pagingData) {
        Page<City> cities = cityService.getCitiesPage(cityName, pagingData);
        Page<CityDto> cityPagingData = new PageImpl<>(
                cityMapper.mapCityEntitiesToDtos(cities.getContent()),
                pagingData,
                cities.getTotalElements()
        );

        return ResponseEntity.ok().body(cityPagingData);
    }

    @GetMapping("/cities")
    public List<CityDto> getCities() {
        return cityMapper.mapCityEntitiesToDtos(cityService.getCities());
    }

    @PreAuthorize("hasAuthority(\"" + ADMIN_ROLE + "\")")
    @PutMapping("/cities")
    public void updateCity(@RequestBody CityDto city) {
        cityService.updateCity(cityMapper.mapCityDtoToEntity(city));
    }
}
