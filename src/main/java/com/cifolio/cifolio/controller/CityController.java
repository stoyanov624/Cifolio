package com.cifolio.cifolio.controller;

import com.cifolio.cifolio.service.city.CityService;
import com.cifolio.cifolio.converters.CityDtoToEntityConverter;
import com.cifolio.cifolio.converters.CityEntityToDtoConverter;
import com.cifolio.cifolio.dto.CityDto;
import com.cifolio.cifolio.model.city.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;

import java.util.stream.Collectors;
import static com.cifolio.cifolio.constants.CityConstants.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/cities")
@AllArgsConstructor
public class CityController {
    private final CityService cityService;
    private final CityDtoToEntityConverter dtoToCityConverter;
    private final CityEntityToDtoConverter cityToDtoConverter;

    @GetMapping
    public Page<CityDto> getCitiesPage(
            @RequestParam(required = false) String cityName,
            @PageableDefault(size = DEFAULT_PAGE_SIZE) Pageable pagingData) {
        Page<City> cities = cityService.getCitiesPage(cityName, pagingData);
        return new PageImpl<>(
                cities.getContent()
                        .stream()
                        .map(cityToDtoConverter)
                        .collect(Collectors.toList()), pagingData, cities.getTotalElements());
    }

    @PutMapping
    public ResponseEntity updateCity(@RequestBody CityDto city) {
        try {
            cityService.updateCity(dtoToCityConverter.apply(city));
            return ResponseEntity.ok().body("Success!");
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error occurred!");
        }
    }
}
