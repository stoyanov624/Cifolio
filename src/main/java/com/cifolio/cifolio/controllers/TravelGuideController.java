package com.cifolio.cifolio.controllers;

import com.cifolio.cifolio.dtos.guide.TravelGuideDto;
import com.cifolio.cifolio.mappers.guide.GuideMapper;
import com.cifolio.cifolio.models.city.TravelGuide;
import com.cifolio.cifolio.service.guide.TravelGuideService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
@Slf4j
public class TravelGuideController {
    private final TravelGuideService travelGuideService;
    private final GuideMapper guideMapper;

    @GetMapping("/guides" )
    public List<TravelGuideDto> getGuidePage() {
        List<TravelGuide> guides = travelGuideService.getGuides();
        return guideMapper.mapGuideEntitiesToGuideDto(guides);
    }

    @PutMapping("/guides" )
    public void updateTravelGuide(@Valid @RequestBody() TravelGuideDto guide) {
        travelGuideService.updateTravelGuide(guideMapper.mapGuideDtoToEntity(guide));
    }

    @PostMapping("/guides" )
    public TravelGuide createNewGuide(@Valid @RequestBody() TravelGuideDto guideDto) {
        return travelGuideService.createTravelGuide(guideMapper.mapGuideDtoToEntity(guideDto));
    }

    @DeleteMapping("/guides/{guideId}" )
    public void deleteGuide(@PathVariable(name = "guideId") @Min(1) Long guideId) {
        travelGuideService.deleteTravelGuideById(guideId);
    }
}
