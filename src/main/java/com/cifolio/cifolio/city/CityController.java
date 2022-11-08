package com.cifolio.cifolio.city;

import com.cifolio.cifolio.dtos.CityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import static com.cifolio.cifolio.city.CityConstants.*;

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
            @PageableDefault(size = DEFAULT_PAGE_SIZE) Pageable pagingData) {

        return cityService.getCitiesPage(cityName, pagingData);
    }

    @PutMapping
    public void updateCity(@RequestBody CityDto city) {
        cityService.updateCity(city);
    }
}
