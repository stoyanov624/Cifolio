package com.cifolio.cifolio.controllers;

import com.cifolio.cifolio.dtos.guide.TravelGuideDto;
import com.cifolio.cifolio.mappers.guide.GuideMapper;
import com.cifolio.cifolio.models.city.TravelGuide;
import com.cifolio.cifolio.service.guide.TravelGuideService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class TravelGuideController {
    private final TravelGuideService travelGuideService;
    private final GuideMapper guideMapper;

    @GetMapping("/guides" )
    public ResponseEntity<?> getGuidePage() {
        List<TravelGuide> guides = travelGuideService.getGuides();
        return ResponseEntity.ok().body(guideMapper.mapGuideEntitiesToGuideDto(guides));
    }

    @PutMapping("/guides" )
    public ResponseEntity<?> updateTravelGuide(
            @RequestBody() TravelGuideDto guide) {
        travelGuideService.updateTravelGuide(guideMapper.mapGuideDtoToEntity(guide));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/guides" )
    public ResponseEntity<?> createNewGuide(@RequestBody() TravelGuideDto guideDto) {
        TravelGuide createdTravelGuide = travelGuideService.createTravelGuide(guideMapper.mapGuideDtoToEntity(guideDto));
        return ResponseEntity.ok().body(createdTravelGuide);
    }

    @DeleteMapping("/guides/{guideId}" )
    public ResponseEntity<?> deleteGuide(@PathVariable(name = "guideId") Long guideId) {
        travelGuideService.deleteTravelGuideById(guideId);
        return ResponseEntity.ok().build();
    }
}