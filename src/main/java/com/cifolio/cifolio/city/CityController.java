package com.cifolio.cifolio.city;

import com.cifolio.cifolio.dtos.CityDto;
import com.cifolio.cifolio.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/cities")
public class CityController {
    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public Page<CityDto> getCitiesPage(
            @RequestParam(required = false) String cityName,
            @RequestParam(defaultValue = CityConstants.DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = CityConstants.DEFAULT_PAGE_SIZE) int pageSize) {

        Pageable pagingData = PageRequest.of(page, pageSize);
        return cityService.getCitiesPage(cityName, pagingData);
    }

    @PutMapping
    public void updateCity(@RequestBody CityDto city) {
        cityService.updateCity(city);
    }
}
