package com.cifolio.cifolio.controller;

import com.cifolio.cifolio.dto.guide.TravelGuideDto;
import com.cifolio.cifolio.mapper.guide.GuideMapper;
import com.cifolio.cifolio.model.city.TravelGuide;
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
        try {
            List<TravelGuide> guides = travelGuideService.getGuides();
            return ResponseEntity.ok().body(guideMapper.mapGuideEntitiesToGuideDto(guides));
        } catch (Exception exception) {
            log.info(exception.getMessage());
            return ResponseEntity.badRequest().body("Unable to add city to guide!");
        }
    }

    @PutMapping("/guides" )
    public ResponseEntity<?> updateTravelGuide(
            @RequestBody() TravelGuideDto guide) {
        try {
            travelGuideService.updateTravelGuide(
                    guideMapper.mapGuideDtoToEntity(guide)
            );
            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            log.info(exception.getMessage());
            return ResponseEntity.badRequest().body("Unable to add city to guide!");
        }
    }

    @PostMapping("/guides" )
    public ResponseEntity<?> createNewGuide(@RequestBody() TravelGuideDto guideDto) {
        try {
            TravelGuide createdTravelGuide = travelGuideService.createTravelGuide(
                    guideMapper.mapGuideDtoToEntity(guideDto)
            );
            return ResponseEntity.ok().body(createdTravelGuide);
        } catch (Exception exception) {
            log.info(exception.getMessage());
            return ResponseEntity.badRequest().body("Unable to create new guide!");
        }
    }

    @DeleteMapping("/guides/{guideId}" )
    public ResponseEntity<?> deleteGuide(@PathVariable(name = "guideId") Long guideId) {
        try {
            travelGuideService.deleteTravelGuideById(guideId);
            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            log.info(exception.getMessage());
            return ResponseEntity.badRequest().body("Unable to create new guide!");
        }
    }
}
