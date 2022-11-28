package com.cifolio.cifolio.service.guide;

import com.cifolio.cifolio.model.city.TravelGuide;
import com.cifolio.cifolio.repository.TravelGuideRepository;
import org.springframework.stereotype.Service;
import java.util.List;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TravelGuideService {
    private final TravelGuideRepository travelGuideRepository;

    public TravelGuide createTravelGuide(TravelGuide guideToCreate) {
        return travelGuideRepository.save(guideToCreate);
    }

    public void updateTravelGuide(TravelGuide updatedGuide) {
        TravelGuide guide = travelGuideRepository.findById(updatedGuide.getId())
                .orElseThrow(() -> new IllegalArgumentException("Guide with that ID not found!"));

        guide.setName(updatedGuide.getName());
        guide.setCities(updatedGuide.getCities());
        travelGuideRepository.save(guide);
    }

    public List<TravelGuide> getGuides() {
        return travelGuideRepository.findAll();
    }

    public void deleteTravelGuideById(Long guideId) {
        TravelGuide guide = travelGuideRepository.findById(guideId)
                .orElseThrow(() -> new IllegalArgumentException("Guide with that ID not found!"));

        travelGuideRepository.delete(guide);
    }
}
