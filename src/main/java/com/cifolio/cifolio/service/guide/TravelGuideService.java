package com.cifolio.cifolio.service.guide;

import com.cifolio.cifolio.model.city.City;
import com.cifolio.cifolio.model.city.TravelGuide;
import com.cifolio.cifolio.repository.CityRepository;
import com.cifolio.cifolio.repository.TravelGuideRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TravelGuideService {
    private final TravelGuideRepository travelGuideRepository;
    private final CityRepository cityRepository;

    public TravelGuide createTravelGuide(TravelGuide guideToCreate) {
        return travelGuideRepository.save(guideToCreate);
    }

    public void addCityToGuide(Long guideId, Long cityId) {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new NoSuchElementException("City not found!"));

        travelGuideRepository.findById(guideId)
                .map(guide -> guide.addCity(city))
                .map(travelGuideRepository::save)
                .orElseThrow(() -> new NoSuchElementException("Guide not found!"));
    }

    public List<TravelGuide> getGuides() {
        return travelGuideRepository.findAll();
    }
}
