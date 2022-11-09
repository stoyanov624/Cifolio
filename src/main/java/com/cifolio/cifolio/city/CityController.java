package com.cifolio.cifolio.city;

import com.cifolio.cifolio.converters.CityDtoToEntityConverter;
import com.cifolio.cifolio.converters.CityEntityToDtoConverter;
import com.cifolio.cifolio.dtos.CityDto;
import com.cifolio.cifolio.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;

import java.util.stream.Collectors;
import static com.cifolio.cifolio.city.CityConstants.*;

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
    public void updateCity(@RequestBody CityDto city) {
        cityService.updateCity(dtoToCityConverter.apply(city));
    }
}
