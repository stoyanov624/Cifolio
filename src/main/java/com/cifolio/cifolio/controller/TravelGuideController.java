package com.cifolio.cifolio.controller;

import com.cifolio.cifolio.dto.guide.TravelGuideDto;
import com.cifolio.cifolio.model.city.TravelGuide;
import com.cifolio.cifolio.service.guide.TravelGuideService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class TravelGuideController {
    private final TravelGuideService travelGuideService;

    @PutMapping("/guides" )
    public ResponseEntity addCityToGuide(
            @RequestParam() Long guideId,
            @RequestParam() Long cityId) {
        try {
            travelGuideService.addCityToGuide(guideId, cityId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseEntity.badRequest().body("Unable to add city to guide!");
        }
    }

    @PostMapping("/guides" )
    public ResponseEntity createNewGuide(@RequestBody() TravelGuideDto guideDto) {
        try {
            TravelGuide guideToCreate = new TravelGuide(guideDto.getName());
            travelGuideService.createTravelGuide(guideToCreate);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseEntity.badRequest().body("Unable to create new guide!");
        }
    }
}
