package com.cifolio.cifolio.service.guide;

import com.cifolio.cifolio.model.city.TravelGuide;
import com.cifolio.cifolio.repository.TravelGuideRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TravelGuideService {
    private final TravelGuideRepository travelGuideRepository;

    public void createTravelGuide(TravelGuide guideToCreate) {
        travelGuideRepository.save(guideToCreate);
    }
}
