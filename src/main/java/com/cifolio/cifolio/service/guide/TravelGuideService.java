package com.cifolio.cifolio.service.guide;

import com.cifolio.cifolio.model.city.City;
import com.cifolio.cifolio.model.city.TravelGuide;
import com.cifolio.cifolio.repository.CityRepository;
import com.cifolio.cifolio.repository.TravelGuideRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TravelGuideService {
    private final TravelGuideRepository travelGuideRepository;
    private final CityRepository cityRepository;

    public void createTravelGuide(TravelGuide guideToCreate) {
        travelGuideRepository.save(guideToCreate);
    }

    public void addCityToGuide(Long guideId, Long cityId) {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new NoSuchElementException("City not found!"));

        travelGuideRepository.findById(guideId)
                .map(guide -> guide.addCity(city))
                .map(travelGuideRepository::save)
                .orElseThrow(() -> new NoSuchElementException("Guide not found!"));
    }
}
