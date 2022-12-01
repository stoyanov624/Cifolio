package com.cifolio.cifolio.service.guide;

import com.cifolio.cifolio.models.city.TravelGuide;
import com.cifolio.cifolio.repositories.TravelGuideRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TravelGuideService {
    private final TravelGuideRepository travelGuideRepository;

    public TravelGuide createTravelGuide(TravelGuide guideToCreate) {
        return travelGuideRepository.save(guideToCreate);
    }

    public void updateTravelGuide(TravelGuide updatedGuide) {
        TravelGuide guide = travelGuideRepository.findById(updatedGuide.getId())
                .orElseThrow(() -> new EntityNotFoundException("Unable to update! Guide with ID: " + updatedGuide.getId() + " not found!"));

        guide.setName(updatedGuide.getName());
        guide.setCities(updatedGuide.getCities());
        travelGuideRepository.save(guide);
    }

    public List<TravelGuide> getGuides() {
        return travelGuideRepository.findAll();
    }

    public void deleteTravelGuideById(Long guideId) {
        TravelGuide guide = travelGuideRepository.findById(guideId)
                .orElseThrow(() -> new EntityNotFoundException("Unable to delete! Guide with ID: " + guideId + " not found!"));

        travelGuideRepository.delete(guide);
    }
}
