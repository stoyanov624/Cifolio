package com.cifolio.cifolio.controller;

import com.cifolio.cifolio.mapper.city.CityMapper;
import com.cifolio.cifolio.service.city.CityService;
import com.cifolio.cifolio.dto.city.CityDto;
import com.cifolio.cifolio.model.city.City;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.cifolio.cifolio.constants.CityConstants.*;
import static com.cifolio.cifolio.constants.UserConstants.ADMIN_ROLE;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;
    private final CityMapper cityMapper;

    @GetMapping("/cities")
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

    @PreAuthorize("hasAuthority(\"" + ADMIN_ROLE + "\")")
    @PutMapping("/cities")
    public ResponseEntity<Void> updateCity(@RequestBody CityDto city) {
        try {
            cityService.updateCity(cityMapper.mapCityDtoToEntity(city));
            return ResponseEntity.ok().build();
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
